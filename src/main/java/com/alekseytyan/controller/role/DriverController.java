package com.alekseytyan.controller.role;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/driver")
public class DriverController {

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @ModelAttribute("driver")
    public DriverDTO getDriver() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DRIVER"))) {
            return driverService.convertToDTO(driverService.findDriverByUser(auth.getName()));
        }
        throw new RuntimeException("NoSuchDriverException");
    }

    @GetMapping(value = "/info")
    public String getInfo(Model model) {
        Long orderId = getDriver().getOrder().getId();
        List<Driver> drivers = driverService.findCoDrivers(orderId);
        List<DriverDTO> coDrivers = drivers != null ? driverService.convertToDTO(drivers) : new ArrayList<>();
        model.addAttribute("coDrivers", coDrivers);
        return "role/driver/driverInfo";
    }

    @GetMapping(value = "/order")
    public String getDutyOrders() {
        return "role/driver/driverOrder";
    }
}
