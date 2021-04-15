package com.alekseytyan.controller;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/user")
public class UserController {

    @RequestMapping(value = "/info")
    public String getUserInfo() {
        return "info/userInfo";
    }
}
