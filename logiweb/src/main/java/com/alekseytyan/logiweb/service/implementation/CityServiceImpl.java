package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.dto.CityDTO;
import com.alekseytyan.logiweb.listener.DataSourceEventPublisher;
import com.alekseytyan.logiweb.dao.api.CityDao;
import com.alekseytyan.logiweb.entity.City;
import com.alekseytyan.logiweb.service.api.CityService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CityServiceImpl extends AbstractServiceImpl<City, CityDao, CityDTO, String> implements CityService {

    @Autowired
    public CityServiceImpl(CityDao dao,
                           ModelMapper mapper,
                           DataSourceEventPublisher publisher) {
        super(dao, mapper, publisher, CityDTO.class, City.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> findAllNames() {
        return getDao().findAllNames();
    }
}
