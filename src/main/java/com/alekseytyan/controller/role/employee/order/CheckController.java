package com.alekseytyan.controller.role.employee.order;

import com.alekseytyan.service.api.OrderService;
import com.alekseytyan.util.LoadChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller checks if user input new order correctly.
 * If not, controller return user to the adding order page to correct errors.
 */
@Controller
@RequestMapping(value = "/employee")
public class CheckController {

    private final OrderService orderService;

    @Autowired
    public CheckController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/checkPoints")
    public String checkRoutePoints(Model model, @RequestParam Long id) {

        LoadChecker loadChecker = orderService.checkRoutePoints(id);

        if(loadChecker.hasErrors()) {
            model.addAttribute("loadChecker", loadChecker);
            return "role/employee/order/loadErrorPage";
        } else {
            return "redirect:/employee/suit-truck/" + id;
        }
    }
}
