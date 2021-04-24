package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.RoutePointDao;
import com.alekseytyan.dto.RoutePointDTO;
import com.alekseytyan.entity.RoutePoint;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoutePointServiceImpl extends AbstractServiceImpl<RoutePoint, RoutePointDao, RoutePointDTO, Long> {
    public RoutePointServiceImpl(RoutePointDao dao, ModelMapper mapper) {
        super(dao, mapper, RoutePointDTO.class, RoutePoint.class);
    }
}
