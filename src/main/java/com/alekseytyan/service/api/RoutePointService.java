package com.alekseytyan.service.api;

import com.alekseytyan.dto.RoutePointDTO;
import com.alekseytyan.entity.RoutePoint;

public interface RoutePointService extends AbstractService<RoutePoint, RoutePointDTO, Long> {
    Long findOrderId(Long routePointId);
}
