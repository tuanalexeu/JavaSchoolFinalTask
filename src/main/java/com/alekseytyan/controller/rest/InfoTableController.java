package com.alekseytyan.controller.rest;

import com.alekseytyan.dto.*;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.LorryService;
import com.alekseytyan.service.api.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/info-table")
@RequiredArgsConstructor
public class InfoTableController {

    private final OrderService orderService;
    private final DriverService driverService;
    private final LorryService lorryService;

    @GetMapping(value = "/orders", produces = "application/json")
    public List<OrderDTO> getOrders() {
        return orderService.findVerified();
    }

    @GetMapping(value = "/driver-stats", produces = "application/json")
    public DriverStatsDTO getDriverStats() {
        return driverService.getStatistics();
    }

    @GetMapping(value = "/lorry-stats", produces = "application/json")
    public LorryStatsDTO getLorryStats() {
        return lorryService.getStatistics();
    }

}
