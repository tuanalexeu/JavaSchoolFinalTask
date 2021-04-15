package com.alekseytyan.dto;

import com.alekseytyan.entity.enums.RouteType;
import lombok.Data;

@Data
public class RoutePointDTO {

    private int id;

    private CityDTO city;

    private RouteType type;

    private LoadDTO load;

    private OrderDTO order;
}
