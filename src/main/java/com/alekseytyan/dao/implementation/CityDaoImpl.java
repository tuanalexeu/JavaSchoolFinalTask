package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.CityDao;
import com.alekseytyan.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City> implements CityDao {
}
