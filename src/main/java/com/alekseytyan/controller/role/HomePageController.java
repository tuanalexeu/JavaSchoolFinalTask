package com.alekseytyan.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @RequestMapping(value = "/homePage")
    public String homePage() {
        // TODO return page according to user role
        return "welcome/index";
    }
}
