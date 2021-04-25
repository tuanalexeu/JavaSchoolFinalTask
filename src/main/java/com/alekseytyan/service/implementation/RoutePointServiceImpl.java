package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.RoutePointDao;
import com.alekseytyan.dto.RoutePointDTO;
import com.alekseytyan.entity.RoutePoint;
import com.alekseytyan.service.api.RoutePointService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoutePointServiceImpl extends AbstractServiceImpl<RoutePoint, RoutePointDao, RoutePointDTO, Long> implements RoutePointService {
    public RoutePointServiceImpl(RoutePointDao dao, ModelMapper mapper) {
        super(dao, mapper, RoutePointDTO.class, RoutePoint.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findOrderId(Long routePointId) {
        return getDao().findById(routePointId).getOrder().getId();
    }
}
