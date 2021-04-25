package com.alekseytyan.controller.role.employee.order;

import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.dto.RoutePointDTO;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.LorryService;
import com.alekseytyan.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class OrderCrudController {

    private final OrderService orderService;


    @Autowired
    public OrderCrudController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(value = "/add-order")
    public String addOrder(Model model) {

        OrderDTO orderDTO = orderService.save(new OrderDTO());

        model.addAttribute("newRoutePoint", new RoutePointDTO());
        model.addAttribute("order", orderDTO);

        return "role/employee/order/edit-order";
    }

    @GetMapping(value = "/edit-order/{id}")
    public String editOrder(Model model, @PathVariable Long id) {

        OrderDTO orderDTO = orderService.findById(id);

        model.addAttribute("newRoutePoint", new RoutePointDTO());
        model.addAttribute("order", orderDTO);

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
}
