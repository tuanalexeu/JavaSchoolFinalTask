package com.alekseytyan.controller.role.admin;

import com.alekseytyan.dto.UserDTO;
import com.alekseytyan.entity.User;
import com.alekseytyan.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin")
    public String getUsers(Model model) {

        List<UserDTO> users = userService.findAll();
        model.addAttribute("users", users);

        return "role/admin/users";
    }
}
