package com.alekseytyan.controller.role.employee;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.*;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.LorryService;
import com.alekseytyan.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final OrderService orderService;
    private final LorryService lorryService;
    private final DriverService driverService;
    private final CityService cityService;

    @Autowired
    public EmployeeController(OrderService orderService,
                              LorryService lorryService,
                              DriverService driverService,
                              CityService cityService) {
        this.orderService = orderService;
        this.lorryService = lorryService;
        this.driverService = driverService;
        this.cityService = cityService;
    }

    @GetMapping(path = "/orders")
    public String showOrders(Model model) {

        List<OrderDTO> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        model.addAttribute("newOrder", new Order());

        return "role/employee/order/orders";
    }

    @GetMapping(path = "/lorries")
    public String showLorries(Model model) {

        List<LorryDTO> lorries = lorryService.findAll();
        List<String> cities = cityService.findAllNames();

        model.addAttribute("lorries", lorries);
        model.addAttribute("cities", cities);

        model.addAttribute("newLorry", new Lorry());

        return "role/employee/lorry/lorries";
    }

    @GetMapping(path = "/drivers")
    public String showDrivers(Model model) {

        List<DriverDTO> drivers = driverService.findAll();
        List<String> cities = cityService.findAllNames();

        model.addAttribute("newDriver", new Driver());

        model.addAttribute("drivers", drivers);
        model.addAttribute("cities", cities);

        return "role/employee/driver/drivers";
    }

}
