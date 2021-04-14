package com.alekseytyan.dto;

import com.alekseytyan.entity.Driver;
import com.alekseytyan.entity.Lorry;
import com.alekseytyan.entity.RoutePoint;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class OrderDTO {

    private int id;

    private boolean isFinished;

    private List<RoutePoint> routePoints;

    private Lorry lorry;

    private Set<Driver> drivers;
}
