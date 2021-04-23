package com.alekseytyan.controller.role;

import com.alekseytyan.entity.*;
import com.alekseytyan.entity.enums.UserRole;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.LorryService;
import com.alekseytyan.service.api.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    private final static Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private final OrderService orderService;
    private final LorryService lorryService;
    private final DriverService driverService;
    private final CityService cityService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public EmployeeController(OrderService orderService,
                              LorryService lorryService,
                              DriverService driverService,
                              CityService cityService,
                              PasswordEncoder passwordEncoder) {
        this.orderService = orderService;
        this.lorryService = lorryService;
        this.driverService = driverService;
        this.cityService = cityService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/orders")
    public String getOrders(Model model) {

        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        model.addAttribute("newOrder", new Order());

        return "role/employee/order/orders";
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

        return "role/employee/lorry-crud/lorries";
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

        return "role/employee/driver-crud/drivers";
    }

    @PostMapping(value = "/add-driver")
    public String addDriver(@ModelAttribute Driver driver) {

        logger.info("User password before encryption: " + driver.getUser().getPassword());

        driver.getUser().setPassword(passwordEncoder.encode(driver.getUser().getPassword()));
        driver.getUser().setRole(UserRole.ROLE_DRIVER);
        driverService.save(driver);

        logger.info("User password after encryption: " + driver.getUser().getPassword());


        return "redirect:/employee/drivers";
    }

    @GetMapping(value = "/edit-driver/{id}")
    public String editDriver(@PathVariable Long id, Model model) {

        model.addAttribute("editDriver", driverService.findById(id));
        model.addAttribute("cities", cityService.findAll());

        return "role/employee/driver-crud/edit-driver";
    }

    @PostMapping(value = "/edit-driver")
    public String editDriver(@ModelAttribute Driver driver) {

        driverService.update(driver);

        return "redirect:/employee/drivers";
    }

    @GetMapping(value = "/delete-driver/{id}")
    public String deleteDriver(@PathVariable Long id) {

        driverService.deleteById(id);

        return "redirect:/employee/drivers";
    }


    @PostMapping(value = "/add-lorry")
    public String addLorry(@ModelAttribute Lorry lorry) {

        lorryService.save(lorry);

        return "redirect:/employee/lorries";
    }

    @GetMapping(value = "/edit-lorry/{id}")
    public String editLorry(@PathVariable String id, Model model) {

        model.addAttribute("editLorry", lorryService.findById(id));
        model.addAttribute("cities", cityService.findAll());

        return "role/employee/lorry-crud/edit-lorry";
    }

    @PostMapping(value = "/edit-lorry")
    public String editLorry(@ModelAttribute Lorry lorry) {

        lorryService.update(lorry);

        return "redirect:/employee/lorries";
    }

    @GetMapping(value = "/delete-lorry/{id}")
    public String deleteLorry(@PathVariable String id) {

        lorryService.deleteById(id);

        return "redirect:/employee/lorries";
    }

}
