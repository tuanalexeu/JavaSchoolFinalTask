package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.dto.DriverStatsDTO;
import com.alekseytyan.logiweb.dto.LorryDTO;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.util.pathfinding.Route;
import com.alekseytyan.logiweb.entity.Driver;

import java.util.List;

public interface DriverService extends AbstractService<Driver, DriverDTO, Long> {
    DriverDTO findDriverByUser(String email);
    List<DriverDTO> findCoDrivers(Long orderId);
    List<DriverDTO> findSuitableDrivers(OrderDTO orderDTO, Route route, LorryDTO lorryDTO);

    List<DriverDTO> findWithoutUser();

    DriverStatsDTO getStatistics();
}
