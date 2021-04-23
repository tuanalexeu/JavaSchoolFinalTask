package com.alekseytyan.service.api;

import com.alekseytyan.dto.CityDTO;
import com.alekseytyan.entity.City;

import java.util.List;

public interface CityService extends AbstractService<City, CityDTO, String> {
    List<String> findAllNames();
}
