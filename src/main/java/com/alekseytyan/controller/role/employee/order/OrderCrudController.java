package com.alekseytyan.controller.role.employee.order;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.dto.LoadDTO;
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
@RequestMapping("/employee")
public class OrderCrudController {

    private final OrderService orderService;
    private final CityService cityService;

    private final DriverService driverService;
    private final LorryService lorryService;


    @Autowired
    public OrderCrudController(OrderService orderService,
                               CityService cityService,
                               LorryService lorryService,
                               DriverService driverService) {
        this.orderService = orderService;
        this.cityService = cityService;
        this.driverService = driverService;
        this.lorryService = lorryService;
    }

    @ModelAttribute("cityNames")
    public List<String> cityNames() {
        return cityService.findAllNames();
    }

    @ModelAttribute("suitableDrivers")
    public List<DriverDTO> suitableDrivers() {
        return driverService.findAll(); // TODO fix
    }

    @ModelAttribute("suitableLorries")
    public List<LorryDTO> suitableLorries() {
        return lorryService.findAll(); // TODO fix
    }

    @GetMapping(value = "/add-order")
    public String addOrder(Model model) {

        model.addAttribute("newLoad", new LoadDTO());
        model.addAttribute("order", orderService.save(new OrderDTO()));

        return "role/employee/order/add-order";
    }

    @GetMapping(value = "/edit-order/{id}")
    public String editOrder(Model model, @PathVariable Long id) {

        model.addAttribute("newLoad", new LoadDTO());
        model.addAttribute("order", orderService.findById(id));

        return "role/employee/order/edit-order";
    }

    @PostMapping(value = "/save-order")
    public String editOrder(@ModelAttribute OrderDTO orderDTO) {

        orderService.update(orderDTO);

        return "redirect:/employee/orders";
    }

    @GetMapping(value = "/delete-order/{id}")
    public String deleteOrder(@PathVariable Long id) {

        orderService.deleteById(id);

        return "redirect:/employee/orders";
    }

    @PostMapping(value = "/verify-order")
    public String verifyOrder(@ModelAttribute OrderDTO orderDTO) {

        // TODO verify order, if at least one of the conditions isn't met
        //  (the route is impossible, not each load has both loading & unloading points, no truck or drivers have been chosen)
        //  the order must not be seen by user

        return "redirect:/employee/orders";
    }

}
