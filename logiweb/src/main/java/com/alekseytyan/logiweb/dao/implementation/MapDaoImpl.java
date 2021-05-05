package com.alekseytyan.logiweb.dao.implementation;

import com.alekseytyan.logiweb.dao.api.MapDao;
import com.alekseytyan.logiweb.entity.DistanceMap;
import org.springframework.stereotype.Repository;

@Repository
public class MapDaoImpl extends AbstractDaoImpl<DistanceMap, Long> implements MapDao {
    public MapDaoImpl() {
        super(DistanceMap.class);
    }
}
