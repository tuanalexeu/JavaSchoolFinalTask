package com.alekseytyan.service.api;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.Driver;

import java.util.List;

public interface DriverService extends AbstractService<Driver, DriverDTO, Long> {
    DriverDTO findDriverByUser(String email);
    List<DriverDTO> findCoDrivers(Long orderId);
}
