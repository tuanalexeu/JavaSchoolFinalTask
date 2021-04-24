package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.RoutePointDao;
import com.alekseytyan.entity.RoutePoint;
import org.springframework.stereotype.Repository;

@Repository
public class RoutePointDaoImpl extends AbstractDaoImpl<RoutePoint, Long> implements RoutePointDao {
    public RoutePointDaoImpl() {
        super(RoutePoint.class);
    }
}
