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
        Long orderId = driverDTO.getId();

        // find list of co-drivers
        model.addAttribute("coDrivers", driverService.findCoDrivers(orderId));

        // find list of route cities
        model.addAttribute("routeCities", orderService.calculateRouteCities(orderId));

        return "role/driver/driverInfo";
    }

    @PostMapping(value = "/save")
    public String getInfo(Model model, @RequestParam String status) {
        DriverDTO driverDTO = (DriverDTO) model.getAttribute("driver");
        driverDTO.setState(DriverState.valueOf(status));
        driverService.update(driverDTO);

        return "redirect:/driver/info";
    }

    @PostMapping(value = "/save-loads")
    public String saveLoadStatus(@ModelAttribute DriverDTO driverDTO) {

        driverService.update(driverDTO);

        return "redirect:/driver/driverInfo";
    }
}
