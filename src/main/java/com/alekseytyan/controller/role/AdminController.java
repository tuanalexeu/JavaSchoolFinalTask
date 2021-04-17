package com.alekseytyan.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "/admin")
public class AdminController {

    @RequestMapping(value = "/users")
    public String getUsers() {
        return "role/admin/users";
    }
}
