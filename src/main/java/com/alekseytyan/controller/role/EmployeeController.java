package com.alekseytyan.controller.role;

import com.alekseytyan.entity.Driver;
import com.alekseytyan.entity.Lorry;
import com.alekseytyan.entity.Order;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.LorryService;
import com.alekseytyan.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final OrderService orderService;
    private final LorryService lorryService;
    private final DriverService driverService;

    @Autowired
    public EmployeeController(OrderService orderService, LorryService lorryService, DriverService driverService) {
        this.orderService = orderService;
        this.lorryService = lorryService;
        this.driverService = driverService;
    }


    @GetMapping(path = "/orders")
    public String getOrders(Model model) {

        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "role/employee/orders";
    }

    @GetMapping(path = "/lorries")
    public String getLorries(Model model) {

        List<Lorry> lorries = lorryService.findAll();
        model.addAttribute("lorries", lorries);

        return "role/employee/lorries";
    }

    @GetMapping(path = "/drivers")
    public String getDrivers(Model model) {

        List<Driver> drivers = driverService.findAll();
        model.addAttribute("drivers", drivers);

        return "role/employee/drivers";
    }

}
