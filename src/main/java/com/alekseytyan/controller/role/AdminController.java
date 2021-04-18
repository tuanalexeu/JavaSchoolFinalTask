package com.alekseytyan.controller.role;

import com.alekseytyan.entity.User;
import com.alekseytyan.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users")
    public String getUsers(Model model) {

        List<User> users = userService.findAll();
        model.addAttribute("users", users);

        return "role/admin/users";
    }
}
