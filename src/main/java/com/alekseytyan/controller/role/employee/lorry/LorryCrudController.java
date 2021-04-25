package com.alekseytyan.controller.role.employee.lorry;

import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.LorryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String addLorry(@ModelAttribute LorryDTO lorry) {

        lorryService.save(lorry);

        return "redirect:/employee/lorries";
    }

    @GetMapping(value = "/edit-lorry/{id}")
    public String editLorry(@PathVariable String id, Model model) {

        model.addAttribute("editLorry", lorryService.findById(id));
        model.addAttribute("cities", cityService.findAllNames());

        return "role/employee/lorry/edit-lorry";
    }

    @PostMapping(value = "/edit-lorry")
    public String editLorry(@ModelAttribute LorryDTO lorry) {

        lorryService.update(lorry);

        return "redirect:/employee/lorries";
    }

    @GetMapping(value = "/delete-lorry/{id}")
    public String deleteLorry(@PathVariable String id) {

        lorryService.deleteById(id);

        return "redirect:/employee/lorries";
    }

}
