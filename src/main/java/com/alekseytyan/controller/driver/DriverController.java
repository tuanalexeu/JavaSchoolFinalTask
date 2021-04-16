package com.alekseytyan.controller.driver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "/driver")
public class DriverController {

    @RequestMapping(value = "/info")
    public String getInfo() {
        return "info/driverInfo";
    }

    @RequestMapping(value = "/orders")
    public String getDutyOrders() {
        return "driver/driverOrders";
    }
}
