package com.alekseytyan.controller.role.employee.order;

import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.service.api.CityService;
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


    @Autowired
    public OrderCrudController(OrderService orderService,
                               CityService cityService) {
        this.orderService = orderService;
        this.cityService = cityService;
    }

    @ModelAttribute("cityNames")
    public List<String> cityNames() {
        return cityService.findAllNames();
    }

    @GetMapping(value = "/add-order")
    public String addOrder(Model model) {

        model.addAttribute("newLoad", new LoadDTO());
        model.addAttribute("order", orderService.save(new OrderDTO()));

        return "role/employee/order/edit-order";
    }

    @GetMapping(value = "/edit-order/{id}")
    public String editOrder(Model model, @PathVariable Long id) {

        OrderDTO orderDTO = orderService.findById(id);

        model.addAttribute("newLoad", new LoadDTO());
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
