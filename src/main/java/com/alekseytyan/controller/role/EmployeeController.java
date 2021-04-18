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
import org.springframework.web.bind.annotation.PathVariable;
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


    @RequestMapping(path = "/orders")
    public String getOrders(Model model) {

        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        return "role/employee/orders";
    }

    @RequestMapping(path = "/lorries")
    public String getLorries(Model model) {

        List<Lorry> lorries = lorryService.findAll();
        model.addAttribute("lorries", lorries);

        return "role/employee/lorries";
    }

    @RequestMapping(path = "/drivers")
    public String getDrivers(Model model) {

        List<Driver> drivers = driverService.findAll();
        model.addAttribute("drivers", drivers);

        return "role/employee/drivers";
    }

    @RequestMapping(value = "/newOrder")
    public String newOrder() {
        return "role/employee/newOrder";
    }

    @RequestMapping(value = "/editLorry/{id}")
    public String editLorry(@PathVariable Long id, Model model) {
        model.addAttribute("lorry", lorryService.findById(id));
        return "role/employee/editLorry";
    }

    @RequestMapping(value = "/newLorry")
    public String newLorry() {
        return "role/employee/newLorry";
    }

    @RequestMapping(value = "/editDriver/{id}")
    public String editDriver(@PathVariable Long id, Model model) {
        model.addAttribute("driver", driverService.findById(id));
        return "role/employee/editDriver";
    }

    @RequestMapping(value = "/newDriver")
    public String newDriver() {
        return "role/employee/newDriver";
    }

}
