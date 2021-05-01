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
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class OrderCrudController {

    private final OrderService orderService;
    private final LorryService lorryService;
    private final DriverService driverService;
    private final CityService cityService;

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

    @GetMapping(value = "/view-order/{orderId}")
    public String viewOrder(Model model, @PathVariable Long orderId) {

        model.addAttribute("order", orderService.findById(orderId));

        return "role/employee/order/order-info";
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
                            @PathVariable(required = false) String error) {

        OrderDTO orderDTO = orderService.findById(id);

        model.addAttribute("newLoad", new LoadDTO());
        model.addAttribute("order", orderDTO);

        if(error != null) {
            model.addAttribute("error", error);
        }


        model.addAttribute("suitableLorries", lorryService.findSuitableLorries(orderDTO));

        if(orderDTO.getLorry() != null) {

            Route route = orderService.calculateRoute(orderDTO);
            model.addAttribute("suitableDrivers", driverService.findSuitableDrivers(
                                                                        orderDTO.getLorry().getCity().getName(),
                                                                        route.getTime()));
        } else {
            model.addAttribute("suitableDrivers", new ArrayList<DriverDTO>());
        }

        return "role/employee/order/edit-order";
    }

    @GetMapping(value = "/delete-order/{id}")
    public RedirectView deleteOrder(@PathVariable Long id) {

        orderService.deleteById(id);

        return new RedirectView("/employee/orders");
    }

    @PostMapping(value = "/apply-truck")
    public RedirectView applyTruck(@RequestParam Long orderId,
                                   @RequestParam String regNum) {
        OrderDTO orderDTO = orderService.findById(orderId);
        LorryDTO lorryDTO = lorryService.findById(regNum);

        orderDTO.setLorry(lorryDTO);
        orderService.update(orderDTO);

        return new RedirectView("/employee/edit-order/" + orderId);
    }

    @PostMapping(value = "/apply-driver")
    public RedirectView applyDriver(@RequestParam Long orderId,
                                    @RequestParam Long id) {
        OrderDTO orderDTO = orderService.findById(orderId);
        DriverDTO driverDTO = driverService.findById(id);

        driverDTO.setOrder(orderDTO);
        driverDTO.setLorry(orderDTO.getLorry());
        driverService.update(driverDTO);

        orderDTO.getDrivers().add(driverDTO);
        orderService.update(orderDTO);

        return new RedirectView("/employee/edit-order/" + orderId);
    }

    @GetMapping("/verify-order/{orderId}")
    public RedirectView verifyOrder(@PathVariable Long orderId) {

        OrderDTO orderDTO = orderService.findById(orderId);

        if(orderDTO.getLorry() == null) {
            return new RedirectView("/employee/edit-order/" + orderId + "/lorryError");
        }

        Route route = orderService.calculateRoute(orderDTO);
        if(!route.isPossible()) {
            return new RedirectView("/employee/edit-order/" + orderId+ "/routeError");
        }

        if(orderDTO.getDrivers().size() != 2) {
            return new RedirectView("/employee/edit-order" + orderId + "/driverError");
        }


        orderDTO.setVerified(route.isPossible());

        orderService.update(orderDTO);

        return new RedirectView("/employee/orders");
    }

}
