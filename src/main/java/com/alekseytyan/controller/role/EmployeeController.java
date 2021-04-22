package com.alekseytyan.controller.role;

import com.alekseytyan.entity.City;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.entity.Lorry;
import com.alekseytyan.entity.Order;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.LorryService;
import com.alekseytyan.service.api.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

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
    public String getOrders(Model model) {

        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        model.addAttribute("newOrder", new Order());

        return "role/employee/orders";
    }

    @GetMapping(path = "/lorries")
    public String getLorries(Model model) {

        List<Lorry> lorries = lorryService.findAll();
        List<Order> orders = orderService.findAll();
        List<City> cities = cityService.findAll();

        model.addAttribute("lorries", lorries);
        model.addAttribute("orders", orders);
        model.addAttribute("cities", cities);

        model.addAttribute("newLorry", new Lorry());

        return "role/employee/lorries";
    }

    @GetMapping(path = "/drivers")
    public String getDrivers(Model model) {

        List<Driver> drivers = driverService.findAll();
        List<City> cities = cityService.findAll();
        List<Lorry> lorries = lorryService.findAll();
        List<Order> orders = orderService.findAll();

        model.addAttribute("newDriver", new Driver());


        model.addAttribute("drivers", drivers);
        model.addAttribute("cities", cities);
        model.addAttribute("lorries", lorries);
        model.addAttribute("orders", orders);

        return "role/employee/drivers";
    }

    @PostMapping(value = "/add-driver")
    public String addDriver(@ModelAttribute Driver newDriver) {

        logger.info("Add driver: In process");

        newDriver.setUser(newDriver.getUser());
        driverService.save(newDriver);

        return "redirect:/employee/drivers";
    }

}
