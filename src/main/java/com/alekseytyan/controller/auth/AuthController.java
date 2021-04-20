package com.alekseytyan.controller.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping(value = {"/login", "/login/{error}"})
    public String login(@PathVariable(required = false) boolean error) {
        if(hasAnyRole()) {
            return "redirect:/homePage";
        }
        return "auth/login";
    }

    @RequestMapping(value = "/register")
    public String register() {
        if(hasAnyRole()) {
            return "redirect:/homePage";
        }
        return "auth/register";
    }

    @RequestMapping(value = "/forgotPassword")
    public String forgotPassword() {
        if(hasAnyRole()) {
            return "redirect:/homePage";
        }
        return "auth/forgot_password";
    }

    @RequestMapping(value = "/logout")
    public String logOut() {
        if(!hasAnyRole())  {
            return "redirect:/welcome";
        }
        return "auth/logout";
    }

    @RequestMapping(value = "/performLogOut")
    public String performLogout() {
        if(!hasAnyRole())  {
            return "redirect:/welcome";
        }
        return "auth/logout";
    }
}
