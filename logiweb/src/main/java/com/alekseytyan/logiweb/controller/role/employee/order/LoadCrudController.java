package com.alekseytyan.logiweb.controller.role.employee.order;

import com.alekseytyan.logiweb.dto.LoadDTO;
import com.alekseytyan.logiweb.service.api.CityService;
import com.alekseytyan.logiweb.service.api.OrderService;
import com.alekseytyan.logiweb.service.api.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller is used to support CRUD operations with loads
 */
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

    @PostMapping(value = "/add-load")
    public RedirectView addRoutePoint(RedirectAttributes attributes,
                                      Model model,
                                      @ModelAttribute LoadDTO loadDTO) {

        model.addAttribute("cityNames", cityService.findAllNames());

        loadService.save(loadDTO);

        attributes.addAttribute("orderId", loadDTO.getOrder().getId());

        return new RedirectView("/employee/edit-order");
    }

    @GetMapping(value = "/edit-load/{id}")
    public String editLoad(Model model, @PathVariable Long id) {

        model.addAttribute("editLoad", loadService.findById(id));
        model.addAttribute("cityNames", cityService.findAllNames());
        model.addAttribute("order", orderService.findById(loadService.findOrderId(id)));

        return "role/employee/order/edit-load";
    }

    @PostMapping(value = "/save-load")
    public RedirectView editLoad(RedirectAttributes attributes,
                                 @ModelAttribute LoadDTO loadDTO) {

        loadService.update(loadDTO);

        attributes.addAttribute("orderId", loadDTO.getOrder().getId());

        return new RedirectView("/employee/edit-order");
    }

    @GetMapping(value = "/delete-load/{id}")
    public RedirectView deleteLoad(RedirectAttributes attributes,
                                   @PathVariable Long id) {

        // We need order id to return editing process of order after we finish deleting current route point
        Long orderId = loadService.findOrderId(id);

        loadService.deleteById(id);

        if(orderService.findById(orderId).getLoads().isEmpty()) {
            return new RedirectView("/employee/add-order");
        } else {
            attributes.addAttribute("orderId", orderId);
            return new RedirectView("/employee/edit-order");
        }

    }

}
