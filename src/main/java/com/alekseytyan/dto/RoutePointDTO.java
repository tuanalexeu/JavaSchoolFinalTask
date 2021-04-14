package com.alekseytyan.dto;

import com.alekseytyan.entity.City;
import com.alekseytyan.entity.Load;
import com.alekseytyan.entity.Order;
import com.alekseytyan.entity.RoutePoint;
import lombok.Data;

@Data
public class RoutePointDTO {

    private int id;

    private City city;

    private RoutePoint.RouteType type;

    private Load load;

    private Order order;
}
