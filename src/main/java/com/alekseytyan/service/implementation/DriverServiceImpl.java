package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.DriverDao;
import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.service.api.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImpl extends AbstractServiceImpl<Driver, DriverDao, DriverDTO> implements DriverService {

    @Autowired
    public DriverServiceImpl(DriverDao dao, ModelMapper mapper) {
        super(dao, mapper, DriverDTO.class);
    }
}
