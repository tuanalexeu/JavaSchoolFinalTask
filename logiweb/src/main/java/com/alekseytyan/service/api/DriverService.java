package com.alekseytyan.service.api;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.DriverStatsDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.util.pathfinding.Route;
import com.alekseytyan.entity.Driver;

import java.util.List;

public interface DriverService extends AbstractService<Driver, DriverDTO, Long> {
    DriverDTO findDriverByUser(String email);
    List<DriverDTO> findCoDrivers(Long orderId);
    List<DriverDTO> findSuitableDrivers(OrderDTO orderDTO, Route route, LorryDTO lorryDTO);

    List<DriverDTO> findWithoutUser();

    DriverStatsDTO getStatistics();
}
