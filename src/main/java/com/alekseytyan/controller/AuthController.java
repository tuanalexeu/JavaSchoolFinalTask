package com.alekseytyan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {
    @RequestMapping(path = "/login")
    public String login() {
        return "auth/login";
    }

    @RequestMapping(path = "/register")
    public String register() {
        return "auth/register";
    }

    @RequestMapping(path = "/forgotPassword")
    public String forgotPassword() {
        return "auth/forgot_password";
    }
}
