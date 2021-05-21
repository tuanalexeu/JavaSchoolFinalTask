package com.alekseytyan.logiweb.controller.role.employee;

import com.alekseytyan.logiweb.entity.Driver;
import com.alekseytyan.logiweb.entity.Lorry;
import com.alekseytyan.logiweb.service.api.CityService;
import com.alekseytyan.logiweb.service.api.DriverService;
import com.alekseytyan.logiweb.service.api.LorryService;
import com.alekseytyan.logiweb.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Main employee controller is used to display list of drivers, trucks and orders
 */
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

    // Each operation requires list of city names
    @ModelAttribute("cities")
    public List<String> cityNames() {
        return cityService.findAllNames();
    }

    @GetMapping(path = "/orders")
    public String showOrders(Model model,
                             @RequestParam(required = false) Integer size,
                             @RequestParam(required = false) Integer page) {

        model.addAttribute("orders", orderService.findVerified(size, page));
        model.addAttribute("routeList", orderService.calculateRoute(orderService.findVerified(size, page)));

        model.addAttribute("size", size == null ? 10 : size);
        model.addAttribute("page", page == null ? 1 : page);

        return "role/employee/order/orders";
    }

    @GetMapping(path = "/lorries")
    public String showLorries(Model model,
                              @RequestParam(required = false) Integer size,
                              @RequestParam(required = false) Integer page) {

        model.addAttribute("lorries", lorryService.findPage(size, page));
        model.addAttribute("newLorry", new Lorry());

        model.addAttribute("size", size == null ? 10 : size);
        model.addAttribute("page", page == null ? 1 : page);

        return "role/employee/lorry/lorries";
    }

    @GetMapping(path = "/drivers")
    public String showDrivers(Model model,
                              @RequestParam(required = false) Integer size,
                              @RequestParam(required = false) Integer page) {

        model.addAttribute("drivers", driverService.findPage(size, page));
        model.addAttribute("newDriver", new Driver());

        model.addAttribute("size", size == null ? 10 : size);
        model.addAttribute("page", page == null ? 1 : page);

        return "role/employee/driver/drivers";
    }

}
