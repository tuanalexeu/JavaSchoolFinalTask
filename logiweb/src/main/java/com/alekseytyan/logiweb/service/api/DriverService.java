package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.entity.Driver;
import com.alekseytyan.logiweb.util.pathfinding.Route;

import java.util.List;

public interface DriverService extends AbstractService<Driver, DriverDTO, Long> {
    DriverDTO findDriverByUser(String email);
    List<DriverDTO> findCoDrivers(Long orderId);
    List<DriverDTO> findSuitableDrivers(OrderDTO orderDTO, Route route);
}
