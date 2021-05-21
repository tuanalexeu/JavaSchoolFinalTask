package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.CityDTO;
import com.alekseytyan.logiweb.entity.City;

import java.util.List;

public interface CityService extends AbstractService<City, CityDTO, String> {
    List<String> findAllNames();
}
