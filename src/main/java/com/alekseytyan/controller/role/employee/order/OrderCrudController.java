package com.alekseytyan.controller.role.employee.order;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.service.api.*;
import com.alekseytyan.util.Route;
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
    private final MapService mapService;


    @Autowired
    public OrderCrudController(OrderService orderService,
                               CityService cityService,
                               LorryService lorryService,
                               DriverService driverService,
                               MapService mapService) {
        this.orderService = orderService;
        this.cityService = cityService;
        this.driverService = driverService;
        this.lorryService = lorryService;
        this.mapService = mapService;
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

    @GetMapping(value ={"/edit-order/{id}", "/edit-order/{id}/{error}"})
    public String editOrder(Model model,
                            @PathVariable Long id,
                            @PathVariable boolean error) {

        model.addAttribute("newLoad", new LoadDTO());
        model.addAttribute("order", orderService.findById(id));

        if(error) {
            model.addAttribute("error", true);
        }

        return "role/employee/order/edit-order";
    }

    @GetMapping(value = "/delete-order/{id}")
    public String deleteOrder(@PathVariable Long id) {

        orderService.deleteById(id);

        return "redirect:/employee/orders";
    }

    @PostMapping(value = "/save-order")
    public String verifyOrder(@ModelAttribute OrderDTO orderDTO) {

        Route route = orderService.calculateRoute(
                orderService.convertToEntity(
                        orderDTO
                ),
                mapService.convertToEntity(
                        mapService.findAll()
                )
        );
        orderDTO.setVerified(route.isPossible());

        if(!route.isPossible()) {
            return "redirect:/employee/edit-order/" + orderDTO.getId() + "/true";
        }

        orderService.update(orderDTO);

        return "redirect:/employee/orders";
    }

}
