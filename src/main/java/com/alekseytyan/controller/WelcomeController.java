package com.alekseytyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
    @RequestMapping(value = {"/", "/welcome"})
    public String welcomePage(Model model) {
        model.addAttribute("name", "Aleksey T.");
        return "welcome/index";
    }
}
