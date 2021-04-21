package com.alekseytyan.dto;

import com.alekseytyan.entity.enums.RouteType;
import lombok.Data;
import lombok.ToString;

@Data
public class RoutePointDTO {

    private Long id;

    private CityDTO city;

    private RouteType type;

    private LoadDTO load;

    @ToString.Exclude
    private OrderDTO order;
}
