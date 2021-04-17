package com.alekseytyan.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {
    @RequestMapping(path = "/notFound")
    public String notFound() {
        return "error/404";
    }
}
