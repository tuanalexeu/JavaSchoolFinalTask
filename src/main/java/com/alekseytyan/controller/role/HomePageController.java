package com.alekseytyan.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePageController {

    @RequestMapping(path = "/homepage")
    public String homePage() {
        // TODO find out which role a user is in and redirect to a corresponding page
        return null;
    }
}
