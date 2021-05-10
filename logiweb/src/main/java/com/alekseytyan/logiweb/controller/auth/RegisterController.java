package com.alekseytyan.logiweb.controller.auth;

import com.alekseytyan.logiweb.dto.VerificationTokenDTO;
import com.alekseytyan.logiweb.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.listener.reg.OnRegistrationCompleteEvent;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.service.api.VerificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final VerificationService verificationService;
    private final MessageSource messages;
    private final ApplicationEventPublisher eventPublisher;

    private boolean hasAnyRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                || a.getAuthority().equals("ROLE_DRIVER")
                || a.getAuthority().equals("ROLE_EMPLOYEE"));
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        if(hasAnyRole()) {
            return "redirect:/homePage";
        }

        model.addAttribute("user", new UserDTO());

        return "auth/register";
    }

    @PostMapping("/reg-process")
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid
                                                        UserDTO userDto,
                                            HttpServletRequest request,
                                            Errors errors) {

        if(!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            ModelAndView mav = new ModelAndView("auth/register", "user", userDto);
            mav.addObject("message", "Password doesn't match");
            return mav;
        }

        try {
            UserDTO registered = userService.registerNewUserAccount(userDto);

//            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userService.convertToEntity(registered),
                    request.getLocale(), ""));

        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("auth/register", "user", userDto);
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        } catch (RuntimeException ex) {
            return new ModelAndView("auth/email-error", "user", userDto);
        }

        return new ModelAndView("auth/check-email", "user", userDto);
    }

    @GetMapping(value = "/register-confirm")
    public String confirmRegistration(Model model,
                                      WebRequest request,
                                      @RequestParam("token") String token) {

        Locale locale = request.getLocale();

        VerificationTokenDTO verificationToken = verificationService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messages.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/register";
        }

        UserDTO user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            String messageValue = messages.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/register";
        }

        verificationService.delete(verificationToken);

        user.setEmailConfirmed(true);
        userService.update(user);

        return "auth/registration-finished";
    }


}
