package com.alekseytyan.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller(value = "/manager")
public class ManagerController {

//    @RequestMapping(value = "/orders")
//    public String getOrders() {
//        return "list/orders";
//    }

    @RequestMapping(value = "/lorries")
    public String getLorries() {
        return "manager/orders";
    }

    @RequestMapping(value = "/drivers")
    public String getDrivers() {
        return "manager/drivers";
    }

}
