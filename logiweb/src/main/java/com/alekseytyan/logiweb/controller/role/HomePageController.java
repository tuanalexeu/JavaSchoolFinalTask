package com.alekseytyan.logiweb.controller.role;

import com.alekseytyan.logiweb.controller.role.exception.NoSuchRoleException;
import com.alekseytyan.logiweb.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class HomePageController {

    private final UserService userService;

    @Autowired
    public HomePageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/homePage")
    public RedirectView homePage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_EMPLOYEE"))) {
            return new RedirectView("/employee/orders");
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DRIVER"))) {
            if(userService.findById(auth.getName()).getDriver() == null) {
                return new RedirectView("/performLogOut");
            }
            return new RedirectView("/driver/info");
        } else if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return new RedirectView("/admin/users");
        } else {
            throw new NoSuchRoleException("Invalid user role");
        }
    }

    @GetMapping(value = "/profile")
    public String profile() {
        return "auth/profile";
    }
}
