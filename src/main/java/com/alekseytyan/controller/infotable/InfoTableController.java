package com.alekseytyan.controller.infotable;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.LorryService;
import com.alekseytyan.service.api.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/info-table")
public class InfoTableController {

    private final OrderService orderService;
    private final DriverService driverService;
    private final LorryService lorryService;

    @Autowired
    public InfoTableController(OrderService orderService,
                               DriverService driverService,
                               LorryService lorryService) {
        this.orderService = orderService;
        this.driverService = driverService;
        this.lorryService = lorryService;
    }

    @GetMapping(value = "/orders", produces = "application/json")
    public List<OrderDTO> getOrders() {
        return orderService.findVerified();
    }

    @GetMapping(value = "/drivers", produces = "application/json")
    public List<DriverDTO> getDrivers() {
        return driverService.findAll();
    }

    @GetMapping(value = "/lorries")
    public List<LorryDTO> getLorries() {
        return lorryService.findAll();
    }

}
