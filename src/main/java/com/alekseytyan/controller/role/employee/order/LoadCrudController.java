package com.alekseytyan.controller.role.employee.order;

import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.OrderService;
import com.alekseytyan.service.api.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class LoadCrudController {

    private final LoadService loadService;
    private final OrderService orderService;
    private final CityService cityService;

    @Autowired
    public LoadCrudController(LoadService loadService,
                              OrderService orderService,
                              CityService cityService) {
        this.loadService = loadService;
        this.orderService = orderService;
        this.cityService = cityService;
    }

    @ModelAttribute("cityNames")
    public List<String> cityNames() {
        return cityService.findAllNames();
    }

    @PostMapping(value = "/add-load")
    public String addRoutePoint(@ModelAttribute LoadDTO loadDTO) {

        loadService.save(loadDTO);

        return "redirect:/employee/edit-order/" + loadDTO.getOrder().getId();
    }

    @GetMapping(value = "/edit-load/{id}")
    public String editLoad(Model model, @PathVariable Long id) {

        model.addAttribute("editLoad", loadService.findById(id));

        OrderDTO orderDTO = orderService.findById(loadService.findOrderId(id));
        model.addAttribute("order", orderDTO);

        return "role/employee/order/edit-load";
    }

    @PostMapping(value = "/save-load")
    public String editLoad(@ModelAttribute LoadDTO loadDTO) {

        loadService.update(loadDTO);

        return "redirect:/employee/edit-order" + loadDTO.getOrder().getId();
    }

    @GetMapping(value = "/delete-load/{id}")
    public String deleteLoad(@PathVariable Long id) {

        // We need order id to return editing process of order after we finish deleting current route point
        Long orderId = loadService.findOrderId(id);

        loadService.deleteById(id);

        return "redirect:/employee/edit-order/" + orderId;

    }

}
