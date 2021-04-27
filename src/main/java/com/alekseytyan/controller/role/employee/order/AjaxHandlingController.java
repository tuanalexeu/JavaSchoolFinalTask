package com.alekseytyan.controller.role.employee.order;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.LorryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller is responsible for finding available lorries and drivers for the current order
 */
@RestController
@RequestMapping("list-rest")
public class AjaxHandlingController {

    private final LorryService lorryService;
    private final DriverService driverService;

    @Autowired
    public AjaxHandlingController(LorryService lorryService,
                                  DriverService driverService) {
        this.lorryService = lorryService;
        this.driverService = driverService;
    }

    @GetMapping(value = "/truck/{weight}", produces = "application/json")
    public List<LorryDTO> findSuitableLorries(@PathVariable Long weight) {
        return lorryService.findSuitableLorries(weight);
    }

    @GetMapping(value = "/driver/{city}/{hours}", produces = "application/json")
    public List<DriverDTO> findSuitableDrivers(@PathVariable String city, @PathVariable Long hours) {
        return driverService.findSuitableDrivers(city, hours);
    }
}
