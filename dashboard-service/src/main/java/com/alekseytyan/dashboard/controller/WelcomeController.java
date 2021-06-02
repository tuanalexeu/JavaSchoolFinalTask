package com.alekseytyan.dashboard.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class WelcomeController {

    /**
     * Check if authorized user has any role in Spring security
     * @return - true, if they have
     */
    private boolean isAuthenticated() {
        return SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
                //when Anonymous Authentication is enabled
                !(SecurityContextHolder.getContext().getAuthentication()
                        instanceof AnonymousAuthenticationToken);
    }

    @GetMapping(value = "/")
    public String welcomePage() {
        if(isAuthenticated()) {
            return "redirect:/profile";
        }
        return "dashboard";
    }
}
