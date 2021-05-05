package com.alekseytyan.logiweb.controller.role;

import com.alekseytyan.logiweb.controller.role.exception.NoSuchRoleException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomePageController {

    @GetMapping(value = "/homePage")
    public RedirectView homePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))) {
            return new RedirectView("/employee/orders");
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DRIVER"))) {
            return new RedirectView("/driver/info");
        } else {
            throw new NoSuchRoleException("Invalid user role");
        }
    }

    @GetMapping(value = "/profile")
    public String profile() {
        return "auth/profile";
    }
}
