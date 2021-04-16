package com.alekseytyan.controller.auth;

import com.alekseytyan.entity.User;
import com.alekseytyan.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/login", "/login/{error}"})
    public String login(@PathVariable(required = false) boolean error) {
        return "auth/login";
    }

    @RequestMapping(path = "/perform_login", method = RequestMethod.POST)
    public String performLogin(@RequestParam String email, @RequestParam String password) {
        List<User> userList = userService.findAll();
        if(userList
                .stream()
                .anyMatch(u -> u.getEmail().equals(email) && u.getPassword().equals(password))) {
            return "redirect:/homepage";
        } else {
            return "redirect:/somethingelse";
        }
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
