package com.alekseytyan.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "/employee")
public class EmployeeController {

    @RequestMapping(value = "/employee-orders")
    public String getOrders() {
        return "role/employee/orders";
    }

    @RequestMapping(value = "/lorries")
    public String getLorries() {
        return "role/employee/lorries";
    }

    @RequestMapping(value = "/drivers")
    public String getDrivers() {
        return "role/employee/drivers";
    }

}
