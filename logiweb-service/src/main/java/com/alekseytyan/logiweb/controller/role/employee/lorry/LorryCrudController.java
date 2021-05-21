package com.alekseytyan.logiweb.controller.role.employee.lorry;

import com.alekseytyan.logiweb.dto.LorryDTO;
import com.alekseytyan.logiweb.service.api.CityService;
import com.alekseytyan.logiweb.service.api.LorryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

/**
 * Controller is used to support CRUD operations with Trucks
 */
@Controller
@RequestMapping(value = "/employee")
public class LorryCrudController {

    private final LorryService lorryService;
    private final CityService cityService;

    @Autowired
    public LorryCrudController(LorryService lorryService, CityService cityService) {
        this.lorryService = lorryService;
        this.cityService = cityService;
    }

    @PostMapping(value = "/add-lorry")
    public RedirectView addLorry(@ModelAttribute LorryDTO lorry) {

        lorryService.save(lorry);

        return new RedirectView("/employee/lorries");
    }

    @GetMapping(value = "/edit-lorry/{id}")
    public String editLorry(@PathVariable String id, Model model) {

        model.addAttribute("editLorry", lorryService.findById(id));
        model.addAttribute("cities", cityService.findAllNames());

        return "role/employee/lorry/edit-lorry";
    }

    @PostMapping(value = "/edit-lorry")
    public RedirectView editLorry(@ModelAttribute LorryDTO lorry) {

        lorryService.update(lorry);

        return new RedirectView("/employee/lorries");
    }

    @GetMapping(value = "/delete-lorry/{id}")
    public RedirectView deleteLorry(@PathVariable String id) {

        lorryService.deleteById(id);

        return new RedirectView("/employee/lorries");
    }

}
