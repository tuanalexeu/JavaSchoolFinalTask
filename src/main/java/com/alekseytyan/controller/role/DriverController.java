package com.alekseytyan.controller.role;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.entity.enums.DriverState;
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

    @ModelAttribute("driverEntity")
    public Driver getDriver() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_DRIVER"))) {
            return driverService.findDriverByUser(auth.getName());
        }
        throw new RuntimeException("NoSuchDriverException");
    }

    @RequestMapping(value = "/info", method = {RequestMethod.GET, RequestMethod.POST})
    public String getInfo(Model model, @RequestParam(name = "status", required = false) String status) {

        // Find driver's id
        Driver driver = (Driver) model.getAttribute("driverEntity");
        Long orderId = driver.getOrder().getId();

        // Check if this is POST request with status parameter
        if(status != null) {

            // if so, update driver entity and push to DB
            driver.setState(DriverState.valueOf(status));
            driverService.update(driver);
        }

        // find list of co-drivers
        List<Driver> drivers = driverService.findCoDrivers(orderId);
        List<DriverDTO> coDrivers = !drivers.isEmpty() ? driverService.convertToDTO(drivers) : new ArrayList<>();
        model.addAttribute("coDrivers", coDrivers);

        // convert driver entity to Dto object and add to model
        DriverDTO driverDTO = driverService.convertToDTO(driver);
        model.addAttribute("driver", driverDTO);

        return "role/driver/driverInfo";
    }
}
