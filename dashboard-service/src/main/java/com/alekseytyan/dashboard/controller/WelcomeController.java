package com.alekseytyan.dashboard.controller;

import com.alekseytyan.dashboard.dao.api.CityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

    @Autowired
    private CityDao cityDao;

    @GetMapping(value = "/")
    public String welcomePage() {
        cityDao.findAll();
        return "index";
    }
}
