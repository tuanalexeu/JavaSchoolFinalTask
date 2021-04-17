package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.DriverDao;
import com.alekseytyan.entity.Driver;
import org.springframework.stereotype.Repository;

@Repository
public class DriverDaoImpl extends AbstractDaoImpl<Driver> implements DriverDao {
    public DriverDaoImpl() {
        super(Driver.class);
    }
}
