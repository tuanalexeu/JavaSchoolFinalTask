package com.alekseytyan.logiweb.controller.authRest;

import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.exception.UserNotFoundException;
import com.alekseytyan.logiweb.service.api.EmailService;
import com.alekseytyan.logiweb.service.api.PasswordService;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.util.response.GenericResponse;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/rest")
@AllArgsConstructor
@PropertySource("classpath:security.properties")
public class RestAuthController {

    private final EmailService emailService;
    private final PasswordService passwordService;
    private final UserService userService;
    private final MessageSource messages;
    private final Environment env;

    @PostMapping("/reset-password")
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) throws MalformedURLException {
        UserDTO user = userService.findById(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }

        String token = UUID.randomUUID().toString();
        passwordService.createPasswordResetTokenForUser(user, token);

        emailService.sendSimpleMessage(constructResetTokenEmail(getAppUrl(request), request.getLocale(), token, user));

        return new GenericResponse(
                messages.getMessage("message.resetPasswordEmail", null,
                        request.getLocale()));
    }

    @GetMapping("/change-password")
    public String showChangePasswordPage(Locale locale, Model model,
                                         @RequestParam("token") String token) {
        String result = passwordService.validatePasswordResetToken(token);
        if(result != null) {
            String message = messages.getMessage("auth.message." + result, null, locale);
            return "redirect:/login?error=true"
                    + locale.getLanguage() + "&message=" + message;
        } else {
            model.addAttribute("token", token);
            return "redirect:/updatePassword" + locale.getLanguage();
        }
    }


    private SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, UserDTO user) {
        String url = contextPath + "/rest/reset-password?token=" + token;
        String message = messages.getMessage("message.resetPassword", null, locale);
        return constructEmail("Reset Password", message + " \r\n" + url, user);
    }

    private SimpleMailMessage constructEmail(String subject, String body, UserDTO user) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject(subject);
        email.setText(body);
        email.setTo(user.getEmail());
        email.setFrom(Objects.requireNonNull(env.getProperty("gmail.address")));
        return email;
    }

    private String getAppUrl(HttpServletRequest request) throws MalformedURLException {
        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port;
    }


}
