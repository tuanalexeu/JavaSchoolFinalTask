package com.alekseytyan.controller.role.employee.driver;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/employee")
public class DriverCrudController {

    private final DriverService driverService;
    private final CityService cityService;

    @Autowired
    public DriverCrudController(DriverService driverService,
                                CityService cityService) {
        this.driverService = driverService;
        this.cityService = cityService;
    }

    @PostMapping(value = "/add-driver")
    public String addDriver(@ModelAttribute DriverDTO driver) {

        driverService.save(driver);

        return "redirect:/employee/drivers";
    }

    @GetMapping(value = "/edit-driver/{id}")
    public String editDriver(@PathVariable Long id, Model model) {

        model.addAttribute("editDriver", driverService.findById(id));
        model.addAttribute("cities", cityService.findAllNames());

        return "role/employee/driver/edit-driver";
    }

    @PostMapping(value = "/edit-driver")
    public String editDriver(@ModelAttribute DriverDTO driver) {

        driverService.update(driver);

        return "redirect:/employee/drivers";
    }

    @GetMapping(value = "/delete-driver/{id}")
    public String deleteDriver(@PathVariable Long id) {

        driverService.deleteById(id);

        return "redirect:/employee/drivers";
    }
}
