package com.alekseytyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/")
    public String greeting(Model model) {
        return "index";
    }

}