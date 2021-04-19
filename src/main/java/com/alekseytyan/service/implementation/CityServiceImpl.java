package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.CityDao;
import com.alekseytyan.dto.CityDTO;
import com.alekseytyan.entity.City;
import com.alekseytyan.service.api.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends AbstractServiceImpl<City, CityDao, CityDTO> implements CityService {

    @Autowired
    public CityServiceImpl(CityDao dao, ModelMapper mapper) {
        super(dao, mapper, CityDTO.class);
    }
}
