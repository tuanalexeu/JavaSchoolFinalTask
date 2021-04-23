package com.alekseytyan.dao.api;

import com.alekseytyan.entity.City;

import java.util.List;

public interface CityDao extends AbstractDao<City, String> {
    List<String> findAllNames();
}
