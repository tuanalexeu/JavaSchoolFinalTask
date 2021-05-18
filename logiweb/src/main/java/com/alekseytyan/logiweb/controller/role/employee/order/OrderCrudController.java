package com.alekseytyan.logiweb.controller.role.employee.order;

import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.dto.LoadDTO;
import com.alekseytyan.logiweb.dto.LorryDTO;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.entity.Driver;
import com.alekseytyan.logiweb.service.api.CityService;
import com.alekseytyan.logiweb.service.api.DriverService;
import com.alekseytyan.logiweb.service.api.LorryService;
import com.alekseytyan.logiweb.service.api.OrderService;
import com.alekseytyan.logiweb.util.error.ErrorChecker;
import com.alekseytyan.logiweb.util.pathfinding.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @GetMapping(value = "/edit-order")
    public String editOrder(Model model,
                            @RequestParam Long orderId,
                            @RequestParam(required = false) Integer errorCode,
                            @RequestParam(required = false) String regNum) {

        OrderDTO orderDTO = orderService.findById(orderId);

        model.addAttribute("newLoad", new LoadDTO());
        model.addAttribute("order", orderDTO);

        if(!orderService.calculateRoute(orderDTO).isPossible()) {
            model.addAttribute("routePossibility",
                    "This order isn't optimal (or possible) due to the route. Please, delete the last load.");
        }

        if(regNum != null) {
            model.addAttribute("truckId", regNum);
        }

        if(errorCode != null) {
            model.addAttribute("error", ErrorChecker.getMessage(errorCode));
        }

        model.addAttribute("suitableLorries", lorryService.findSuitableLorries(orderDTO));

        if(regNum != null) {
            model.addAttribute("suitableDrivers",
                    driverService.findSuitableDrivers(
                            orderDTO,
                            orderService.calculateRoute(orderDTO),
                            lorryService.findById(regNum))
            );
        }

        return "role/employee/order/edit-order";
    }

    @GetMapping(value = "/delete-order/{id}")
    public RedirectView deleteOrder(@PathVariable Long id) {

        orderService.deleteById(id);

        return new RedirectView("/employee/orders");
    }

    @PostMapping(value = "/apply-truck")
    public RedirectView applyTruck(RedirectAttributes attributes,
                                   @RequestParam Long orderId,
                                   @RequestParam String regNum) {

        attributes.addAttribute("orderId", orderId);
        attributes.addAttribute("regNum", regNum);

        return new RedirectView("/employee/edit-order");
    }

    @PostMapping("/verify-order")
    public RedirectView verifyOrder(RedirectAttributes attributes,
                                    @RequestParam Long orderId,
                                    @RequestParam(required = false) String regNum,
                                    @RequestParam(required = false) Long driver1Id,
                                    @RequestParam(required = false) Long driver2Id) {

        OrderDTO orderDTO = orderService.findById(orderId);

        attributes.addAttribute("orderId", orderId);

        if(regNum == null) {
            attributes.addAttribute("errorCode", 1);
            return new RedirectView("/employee/edit-order");
        }
        LorryDTO lorryDTO = lorryService.findById(regNum);


        Route route = orderService.calculateRoute(orderDTO);
        if(!route.isPossible()) {
            attributes.addAttribute("errorCode", 2);
            return new RedirectView("/employee/edit-order");
        }

        if(driver1Id == null && driver2Id == null) {
            attributes.addAttribute("errorCode", 3);
            return new RedirectView("/employee/edit-order");
        }

        if(driver1Id != null && driver2Id != null) {
            DriverDTO driverDTO = driverService.findById(driver1Id);
            if(driver1Id.equals(driver2Id)) {
                driverDTO.setOrder(orderDTO);
                orderDTO.getDrivers().add(driverDTO);
                driverDTO.setLorry(lorryDTO);

                orderDTO.setLorry(lorryDTO);

                driverService.update(driverDTO);
            } else {
                DriverDTO driver2DTO = driverService.findById(driver2Id);

                orderDTO.setLorry(lorryDTO);

                orderDTO.getDrivers().add(driverDTO);
                orderDTO.getDrivers().add(driver2DTO);

                driverDTO.setOrder(orderDTO);
                driver2DTO.setOrder(orderDTO);

                driverDTO.setLorry(lorryDTO);
                driver2DTO.setLorry(lorryDTO);

                driverService.update(driver2DTO);
            }
        } else if(driver1Id == null) {
            DriverDTO driverDTO = driverService.findById(driver2Id);
            driverDTO.setOrder(orderDTO);
            orderDTO.getDrivers().add(driverDTO);
            driverDTO.setLorry(lorryDTO);
            driverService.update(driverDTO);
        } else {
            DriverDTO driverDTO = driverService.findById(driver1Id);
            driverDTO.setOrder(orderDTO);
            orderDTO.getDrivers().add(driverDTO);
            driverDTO.setLorry(lorryDTO);
            driverService.update(driverDTO);
        }

        orderService.update(orderDTO);

        orderDTO.setVerified(route.isPossible());
        orderService.update(orderDTO);

        return new RedirectView("/employee/orders");
    }

}
