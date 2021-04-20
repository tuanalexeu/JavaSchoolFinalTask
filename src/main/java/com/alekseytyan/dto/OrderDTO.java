package com.alekseytyan.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OrderDTO {

    private Long id;

    private boolean isFinished;

    private List<RoutePointDTO> routePoints;

    private LorryDTO lorry;

    private List<DriverDTO> drivers;
}
