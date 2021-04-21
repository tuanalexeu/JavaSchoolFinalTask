package com.alekseytyan.controller.role;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.service.api.DriverService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

    private static final Logger logger = LogManager.getLogger(DriverController.class);

    private final DriverService driverService;

    @Autowired
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @ModelAttribute("driver")
    public DriverDTO getDriver() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DRIVER"))) {

            logger.info("DTO Driver: in process");

            Driver driver = driverService.findDriverByUser(auth.getName());
            DriverDTO driverDTO = driverService.convertToDTO(driver);

            logger.info("DTO Driver: Successful" + driverDTO.toString());

            return driverDTO;
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
