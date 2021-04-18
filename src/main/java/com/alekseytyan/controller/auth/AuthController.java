package com.alekseytyan.controller.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {

    @RequestMapping(value = {"/login", "/login/{error}"})
    public String login(@PathVariable(required = false) boolean error) {
        return "auth/login";
    }

    @RequestMapping(value = "/performLogin")
    public String performLogin() {
        return "auth/performLogin";
    }

    @RequestMapping(value = "/register")
    public String register() {
        return "auth/register";
    }

    @RequestMapping(value = "/forgotPassword")
    public String forgotPassword() {
        return "auth/forgot_password";
    }

    @RequestMapping(value = "/preformLogOut")
    public String logOut() {
        return "auth/logout";
    }

    @RequestMapping(value = "/profile")
    public String profile() {
        return "auth/profile";
    }
}
