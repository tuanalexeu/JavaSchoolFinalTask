package com.alekseytyan.logiweb.controller.rest;

import com.alekseytyan.logiweb.dto.*;
import com.alekseytyan.logiweb.service.api.DriverService;
import com.alekseytyan.logiweb.service.api.LorryService;
import com.alekseytyan.logiweb.service.api.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/info-table")
@RequiredArgsConstructor
public class InfoTableController {

    private final OrderService orderService;
    private final DriverService driverService;
    private final LorryService lorryService;

    @GetMapping(value = "/orders", produces = "application/json")
    public List<OrderStatsDTO> getOrders() {
        return orderService.findVerified()
                .stream()
                .map(o -> {

                    List<DriverDTO> drivers = o.getDrivers();
                    DriverDTO driver1DTO = drivers.get(0);
                    DriverDTO driver2DTO = drivers.size() > 1 ? drivers.get(1) : null;

                    String driver1Name = driver1DTO.getFirstName() + " " + driver1DTO.getLastName();
                    String driver2Name = driver2DTO != null
                            ? driver2DTO.getFirstName() + " " + driver2DTO.getLastName()
                            : null;

                    return new OrderStatsDTO(
                            o.getId(),
                            o.isFinished(),
                            o.getLorry().getRegNum(),
                            driver1Name,
                            driver2Name
                    );

                })
                .limit(15)
                .collect(Collectors.toList());
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
