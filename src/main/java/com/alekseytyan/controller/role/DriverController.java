package com.alekseytyan.controller.role;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.enums.DriverState;
import com.alekseytyan.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        List<DriverDTO> coDrivers = driverService.findCoDrivers(orderId);
        model.addAttribute("coDrivers", coDrivers);

        // convert driver entity to Dto object and add to model
        model.addAttribute("driver", driverDTO);

        return "role/driver/driverInfo";
    }

    @PostMapping(value = "/info")
    public String getInfo(Model model, @RequestParam String status) {
        DriverDTO driverDTO = (DriverDTO) model.getAttribute("driver");
        driverDTO.setState(DriverState.valueOf(status));
        driverService.update(driverDTO);

        return "role/driver/driverInfo";
    }
}
