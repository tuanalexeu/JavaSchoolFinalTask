package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.MapDao;
import com.alekseytyan.dto.MapDTO;
import com.alekseytyan.entity.DistanceMap;
import com.alekseytyan.service.api.MapService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl extends AbstractServiceImpl<DistanceMap, MapDao, MapDTO, Long> implements MapService {
    public MapServiceImpl(MapDao dao, ModelMapper mapper) {
        super(dao, mapper, MapDTO.class, DistanceMap.class);
    }
}
