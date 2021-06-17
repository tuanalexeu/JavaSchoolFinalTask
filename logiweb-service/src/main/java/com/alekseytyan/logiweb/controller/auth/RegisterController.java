package com.alekseytyan.logiweb.controller.auth;

import com.alekseytyan.logiweb.dto.VerificationTokenDTO;
import com.alekseytyan.logiweb.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.listener.reg.OnRegistrationCompleteEvent;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.service.api.VerificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

/**
 * Controller for registration process
 */
@Controller
@AllArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final VerificationService verificationService;
    private final ApplicationEventPublisher eventPublisher;
    private final ThreadPoolTaskScheduler registerScheduler;

    /**
     * Check if authorized user has any role in Spring security
     * @return - true, if they have
     */
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
    public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDTO userDto,
                                            HttpServletRequest request) {

        if(!userDto.getPassword().equals(userDto.getMatchingPassword())) {
            ModelAndView mav = new ModelAndView("auth/register", "user", userDto);
            mav.addObject("message", "Passwords don't match");
            return mav;
        }

        try {
            UserDTO registered = userService.registerNewUserAccount(userDto);

            // Publish delayed task. After 3 days from now unconfirmed user will be deleted
            registerScheduler.scheduleWithFixedDelay(() -> userService.deleteIfUnconfirmed(userDto.getEmail()), 259200000);

            // Publish registration event in order to send email to confirm account
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(userService.convertToEntity(registered),
                    request.getLocale(), ""));

        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView("auth/register", "user", userDto);
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        } catch (RuntimeException ex) {
            return new ModelAndView("error/defaultError", "user", userDto);
        }

        return new ModelAndView("auth/check-email", "user", userDto);
    }

    @GetMapping(value = "/register-confirm")
    public String confirmRegistration(Model model,
                                      WebRequest request,
                                      @RequestParam("token") String token) {

        // Check if received token is correct, redirect to main registration page if not
        VerificationTokenDTO verificationToken = verificationService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = "Invalid token";
            model.addAttribute("message", message);
            return "redirect:/register";
        }

        // If token is correct, check if it's not overdue
        UserDTO user = verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {

            // If 1 day has passed, the token isn't working anymore
            String messageValue = "Token expired";
            model.addAttribute("message", messageValue);
            return "redirect:/register";
        }

        // If token is correct and not expired, we need to delete it and confirm user
        verificationService.delete(verificationToken);

        user.setEmailConfirmed(true);
        userService.update(user);

        return "auth/registration-finished";
    }


}
