package com.alekseytyan.controller.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AuthController {

    private boolean hasAnyRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                || a.getAuthority().equals("ROLE_DRIVER")
                || a.getAuthority().equals("ROLE_EMPLOYEE"));
    }

    @GetMapping(value = {"/login", "/login/{error}"})
    public String login(Model model, @PathVariable(required = false) boolean error) {
        if(hasAnyRole()) {
            return "redirect:/homePage";
        }

        if(error) {
            model.addAttribute("errorMessage", "Invalid email or password!");
        }

        return "auth/login";
    }

    @GetMapping(value = "/register")
    public String register() {
        if(hasAnyRole()) {
            return "redirect:/homePage";
        }
        return "auth/register";
    }

    @GetMapping(value = "/forgotPassword")
    public String forgotPassword() {
        if(hasAnyRole()) {
            return "redirect:/homePage";
        }
        return "auth/forgot_password";
    }

    @GetMapping(value = "/logout")
    public String logOut() {
        if(!hasAnyRole())  {
            return "redirect:/welcome";
        }
        return "auth/logout";
    }

    @GetMapping(value = "/performLogOut")
    public String performLogout() {
        if(!hasAnyRole())  {
            return "redirect:/welcome";
        }
        return "auth/logout";
    }
}
