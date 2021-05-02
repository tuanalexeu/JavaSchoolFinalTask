package com.alekseytyan.controller.role.driver;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.enums.DriverState;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    private final DriverService driverService;
    private final OrderService orderService;

    @Autowired
    public DriverController(DriverService driverService,
                            OrderService orderService) {
        this.driverService = driverService;
        this.orderService = orderService;
    }

    @ModelAttribute("driver")
    public DriverDTO getDriver() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DRIVER"))) {
            return driverService.findDriverByUser(auth.getName());
        }
        throw new RuntimeException("NoSuchDriverException");
    }

    @GetMapping(value = "/info")
    public String getInfo(Model model) {

        // Find driver's id
        DriverDTO driverDTO = (DriverDTO) model.getAttribute("driver");

        if(driverDTO.getOrder() != null) {
            Long orderId = driverDTO.getOrder().getId();

            // find list of co-drivers
            model.addAttribute("coDrivers", driverService.findCoDrivers(orderId));

            // find list of route cities
            model.addAttribute("route", orderService.calculateRoute(orderService.findById(orderId)));

            model.addAttribute("loads", orderService.convertLoadsToMap(driverDTO.getOrder()));
        }

        return "role/driver/driverInfo";
    }

    @PostMapping(value = "/save")
    public RedirectView saveStatus(Model model, @RequestParam String status) {
        DriverDTO driverDTO = (DriverDTO) model.getAttribute("driver");
        driverDTO.setState(DriverState.valueOf(status));
        driverService.update(driverDTO);

        return new RedirectView("/driver/info");
    }

    @PostMapping(value = "/save-loads")
    public RedirectView saveLoads(Model model, @ModelAttribute Map<Long, String> loads) {

        DriverDTO driverDTO = (DriverDTO) model.getAttribute("driver");

        if(driverDTO.getOrder() != null) {
            driverDTO.getOrder().setLoads(orderService.convertLoadsToList(loads, driverDTO));
        }

        driverService.update(driverDTO);

        return new RedirectView("/driver/info");
    }
}
