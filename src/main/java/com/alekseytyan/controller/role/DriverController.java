package com.alekseytyan.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "/driver")
public class DriverController {

    @RequestMapping(value = "/info")
    public String getInfo() {
        return "role/driver/driverInfo";
    }

    @RequestMapping(value = "/driver-orders")
    public String getDutyOrders() {
        return "role/driver/driverOrders";
    }
}
