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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class OrderCrudController {

    private final OrderService orderService;
    private final LorryService lorryService;
    private final DriverService driverService;
    private final CityService cityService;
    private final MapService mapService;


    @Autowired
    public OrderCrudController(OrderService orderService,
                               CityService cityService,
                               MapService mapService,
                               LorryService lorryService,
                               DriverService driverService) {
        this.orderService = orderService;
        this.cityService = cityService;
        this.mapService = mapService;
        this.driverService = driverService;
        this.lorryService = lorryService;
    }

    @ModelAttribute("cityNames")
    public List<String> cityNames() {
        return cityService.findAllNames();
    }

    @GetMapping(value = "/add-order")
    public String addOrder(Model model) {


        model.addAttribute("newLoad", new LoadDTO());
        model.addAttribute("order", orderService.save(new OrderDTO()));
        model.addAttribute("error", false);

        model.addAttribute("suitableLorries", new ArrayList<>());
        model.addAttribute("suitableDrivers", new ArrayList<>());

        return "role/employee/order/edit-order";
    }

    @GetMapping(value ={"/edit-order/{id}", "/edit-order/{id}/{error}"})
    public String editOrder(Model model,
                            @PathVariable Long id,
                            @PathVariable(required = false) boolean error) {

        OrderDTO orderDTO = orderService.findById(id);

        model.addAttribute("newLoad", new LoadDTO());
        model.addAttribute("order", orderDTO);
        model.addAttribute("error", error);

        model.addAttribute("suitableLorries", lorryService.findSuitableLorries(
                                                    orderService.calculateWeight(
                                                            orderService.convertToEntity(orderDTO)
                                                    )
                                                 )
        );


        if(orderDTO.getLorry() != null) {

            Route route = orderService.calculateRoute(
                    orderService.convertToEntity(orderDTO),
                    mapService.convertToEntity(mapService.findAll())
            );

            model.addAttribute("suitableDrivers", driverService.findSuitableDrivers(
                    orderDTO.getLorry().getCity().getName(),
                    route.getTime()));
        }

        return "role/employee/order/edit-order";
    }

    @GetMapping(value = "/delete-order/{id}")
    public String deleteOrder(@PathVariable Long id) {

        orderService.deleteById(id);

        return "redirect:/employee/orders";
    }

    @PostMapping(value = "/verify-order")
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

    @PostMapping(value = "/apply-truck/")
    public String applyTruck(@RequestParam Long orderId, @RequestParam String regNum) {
        OrderDTO orderDTO = orderService.findById(orderId);
        LorryDTO lorryDTO = lorryService.findById(regNum);

        lorryDTO.setOrder(orderDTO);
        lorryService.update(lorryDTO);


        orderDTO.setLorry(lorryDTO);
        orderService.update(orderDTO);

        return "redirect:/employee/edit-order/" + orderId;
    }

    @PostMapping(value = "/apply-driver")
    public String applyDriver(@RequestParam Long orderId, @RequestParam Long id) {
        OrderDTO orderDTO = orderService.findById(orderId);
        DriverDTO driverDTO = driverService.findById(id);

        driverDTO.setOrder(orderDTO);
        driverDTO.setLorry(orderDTO.getLorry());
        driverService.update(driverDTO);

        orderDTO.getDrivers().add(driverDTO);
        orderService.update(orderDTO);

        return "redirect:/employee/edit-order/" + orderDTO;
    }

}
