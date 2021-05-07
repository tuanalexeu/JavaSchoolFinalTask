package com.alekseytyan.controller.role.employee.driver;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView addDriver(@ModelAttribute DriverDTO driver) {

        driverService.save(driver);

        return new RedirectView("/employee/drivers");
    }

    @GetMapping(value = "/edit-driver/{id}")
    public String editDriver(@PathVariable Long id, Model model) {

        model.addAttribute("editDriver", driverService.findById(id));
        model.addAttribute("cities", cityService.findAllNames());

        return "role/employee/driver/edit-driver";
    }

    @PostMapping(value = "/edit-driver")
    public RedirectView editDriver(@ModelAttribute DriverDTO driver) {

        driverService.update(driver);

        return new RedirectView("/employee/drivers");
    }

    @GetMapping(value = "/delete-driver/{id}")
    public RedirectView deleteDriver(@PathVariable Long id) {

        driverService.deleteById(id);

        return new RedirectView("/employee/drivers");
    }
}
