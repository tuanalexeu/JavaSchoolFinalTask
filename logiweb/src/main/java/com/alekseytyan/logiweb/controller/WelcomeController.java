package com.alekseytyan.logiweb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @GetMapping(value = {"/", "/welcome"})
    public String welcomePage() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                                                                || a.getAuthority().equals("ROLE_DRIVER")
                                                                || a.getAuthority().equals("ROLE_EMPLOYEE")))  {
            return "redirect:/homePage";
        }

        // Home page is only accessible to unauthorized users
        return "welcome/index";
    }
}
