package com.alekseytyan.dashboard.dao.implementation;

import com.alekseytyan.dashboard.dao.api.CityDao;
import com.alekseytyan.dashboard.entity.City;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CityDaoImpl extends AbstractDaoImpl<City, String> implements CityDao {
    public CityDaoImpl() {
        super(City.class);
    }

    @Override
    public List<String> findAllNames() {
        return entityManager
                .createNamedQuery("City.findAllNames", String.class)
                .getResultList();
    }
}
