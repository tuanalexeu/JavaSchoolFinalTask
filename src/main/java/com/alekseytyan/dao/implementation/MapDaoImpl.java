package com.alekseytyan.dao.implementation;

import com.alekseytyan.dao.api.MapDao;
import com.alekseytyan.entity.Map;
import org.springframework.stereotype.Repository;

@Repository
public class MapDaoImpl extends AbstractDaoImpl<Map, Long> implements MapDao {
    public MapDaoImpl() {
        super(Map.class);
    }
}
