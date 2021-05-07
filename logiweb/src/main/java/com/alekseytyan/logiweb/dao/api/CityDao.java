package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.City;

import java.util.List;

public interface CityDao extends AbstractDao<City, String> {
    List<String> findAllNames();
}
