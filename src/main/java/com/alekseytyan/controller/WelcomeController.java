package com.alekseytyan.controller;

import com.alekseytyan.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    private UserService userService;

    @Autowired
    public WelcomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("name", "Aleksey T.");
        return "welcome/index";
    }
}
