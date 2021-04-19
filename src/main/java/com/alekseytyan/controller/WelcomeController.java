package com.alekseytyan.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping(value = {"/", "/welcome"})
    public String welcomePage(Model model) {

        model.addAttribute("example", "example message");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null
                && auth.isAuthenticated()
                && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")
                                                                || a.getAuthority().equals("ROLE_DRIVER")
                                                                || a.getAuthority().equals("ROLE_EMPLOYEE")))  {
            return "redirect:/homePage";
        }

        return "welcome/index";
    }
}
