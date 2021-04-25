package com.alekseytyan.controller.role.employee.order;

import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.dto.RoutePointDTO;
import com.alekseytyan.entity.City;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.LoadService;
import com.alekseytyan.service.api.OrderService;
import com.alekseytyan.service.api.RoutePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class RoutePointCrudController {

    private final RoutePointService routePointService;
    private final LoadService loadService;
    private final OrderService orderService;
    private final CityService cityService;

    @Autowired
    public RoutePointCrudController(RoutePointService routePointService,
                                    OrderService orderService,
                                    CityService cityService,
                                    LoadService loadService) {
        this.routePointService = routePointService;
        this.orderService = orderService;
        this.cityService = cityService;
        this.loadService = loadService;
    }

    @ModelAttribute("cityNames")
    public List<String> cityNames() {
        return cityService.findAllNames();
    }

    @PostMapping(value = "/add-routePoint")
    public String addRoutePoint(@ModelAttribute RoutePointDTO routePointDTO) {

        LoadDTO loadDTO = loadService.save(routePointDTO.getLoad());
        routePointDTO.setLoad(loadDTO);

        routePointService.save(routePointDTO);

        return "redirect:/employee/edit-order/" + routePointService.findOrderId(routePointDTO.getId());
    }

    @GetMapping(value = "/edit-routePoint/{id}")
    public String editRoutePoint(Model model, @PathVariable Long id) {

        model.addAttribute("editRoutePoint", routePointService.findById(id));

        OrderDTO orderDTO = orderService.findById(routePointService.findOrderId(id));
        model.addAttribute("order", orderDTO);

        return "role/employee/order/edit-routePoint";
    }

    @PostMapping(value = "/save-routePoint")
    public String editRoutePoint(@ModelAttribute RoutePointDTO routePointDTO) {

        routePointService.update(routePointDTO);

        return "redirect:/employee/edit-order" + routePointDTO.getOrder().getId();
    }

    @GetMapping(value = "/delete-routePoint/{id}")
    public String deleteRoutePoint(@PathVariable Long id) {

        // We need order id to return editing process of order after we finish deleting current route point
        Long orderId = routePointService.findOrderId(id);

        routePointService.deleteById(id);

        return "redirect:/employee/edit-order/" + orderId;

    }

}
