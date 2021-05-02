package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.MapDao;
import com.alekseytyan.entity.DistanceMap;
import org.springframework.stereotype.Repository;

@Repository
public class MapDaoImpl extends AbstractDaoImpl<DistanceMap, Long> implements MapDao {
    public MapDaoImpl() {
        super(DistanceMap.class);
    }
}
