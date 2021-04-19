package com.alekseytyan.controller.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping(value = {"/login", "/login/{error}"})
    public String login(@PathVariable(required = false) boolean error) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                || a.getAuthority().equals("ROLE_DRIVER")
                || a.getAuthority().equals("ROLE_EMPLOYEE")))  {
            return "redirect:/homePage";
        }

        return "auth/login";
    }

    @RequestMapping(value = "/register")
    public String register() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                || a.getAuthority().equals("ROLE_DRIVER")
                || a.getAuthority().equals("ROLE_EMPLOYEE")))  {
            return "redirect:/homePage";
        }

        return "auth/register";
    }

    @RequestMapping(value = "/forgotPassword")
    public String forgotPassword() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                || a.getAuthority().equals("ROLE_DRIVER")
                || a.getAuthority().equals("ROLE_EMPLOYEE")))  {
            return "redirect:/homePage";
        }

        return "auth/forgot_password";
    }

    @RequestMapping(value = "/logout")
    public String logOut() {

        if(SecurityContextHolder.getContext().getAuthentication() == null)  {
            return "redirect:/welcome";
        }

        return "auth/logout";
    }

    @RequestMapping(value = "/performLogOut")
    public String performLogout() {

        if(SecurityContextHolder.getContext().getAuthentication() == null)  {
            return "redirect:/welcome";
        }

        return "auth/logout";
    }

    @RequestMapping(value = "/profile")
    public String profile() {
        return "auth/profile";
    }
}
