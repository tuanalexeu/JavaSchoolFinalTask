package com.alekseytyan.controller.role;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @RequestMapping(path = "/employee-orders")
    public String getOrders() {
        return "role/employee/orders";
    }

    @RequestMapping(path = "/lorries")
    public String getLorries() {
        return "role/employee/lorries";
    }

    @RequestMapping(path = "/drivers")
    public String getDrivers() {
        return "role/employee/drivers";
    }

}
