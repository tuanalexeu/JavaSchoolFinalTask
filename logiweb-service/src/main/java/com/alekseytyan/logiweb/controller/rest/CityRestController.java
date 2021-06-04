package com.alekseytyan.logiweb.controller.rest;

import com.alekseytyan.logiweb.dto.CityDTO;
import com.alekseytyan.logiweb.service.api.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CityRestController {

    private final CityService cityService;

    @GetMapping(value = "/get-cities", produces = "application/json")
    public List<CityDTO> cities() {
        return cityService.findAll();
    }
}
