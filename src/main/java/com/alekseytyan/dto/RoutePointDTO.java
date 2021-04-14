package com.alekseytyan.dto;

import com.alekseytyan.entity.RoutePoint;
import lombok.Data;

@Data
public class RoutePointDTO {

    private int id;

    private CityDTO city;

    private RoutePoint.RouteType type;

    private LoadDTO load;

    private OrderDTO order;
}
