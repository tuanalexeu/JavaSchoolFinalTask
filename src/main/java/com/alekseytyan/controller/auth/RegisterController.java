package com.alekseytyan.controller.auth;

import com.alekseytyan.controller.auth.exception.UserAlreadyExistException;
import com.alekseytyan.dto.UserDTO;
import com.alekseytyan.entity.User;
import com.alekseytyan.service.api.EmailService;
import com.alekseytyan.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegisterController {

    private final EmailService emailService;
    private final UserService userService;

    private boolean hasAnyRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                || a.getAuthority().equals("ROLE_DRIVER")
                || a.getAuthority().equals("ROLE_EMPLOYEE"));
    }

    @Autowired
    public RegisterController(EmailService emailService,
                              UserService userService) {
        this.emailService = emailService;
        this.userService = userService;
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
                                            Errors errors) {

        try {
            User registered = userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException uaeEx) {
            ModelAndView mav = new ModelAndView();
            mav.addObject("message", "An account for that username/email already exists.");
            return mav;
        }

        return new ModelAndView("auth/registration-finished", "user", userDto);
    }

}
