package com.alekseytyan.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

    @RequestMapping(value = "/notFound")
    public String notFound() {
        return "error/404";
    }

    @RequestMapping(value = "/accessDenied")
    public String forbidden() {
        return "error/403";
    }
}
