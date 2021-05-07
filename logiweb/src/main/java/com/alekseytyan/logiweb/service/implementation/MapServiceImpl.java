package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.dto.MapDTO;
import com.alekseytyan.logiweb.listener.DataSourceEventPublisher;
import com.alekseytyan.logiweb.dao.api.MapDao;
import com.alekseytyan.logiweb.entity.DistanceMap;
import com.alekseytyan.logiweb.service.api.MapService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl extends AbstractServiceImpl<DistanceMap, MapDao, MapDTO, Long> implements MapService {
    public MapServiceImpl(MapDao dao,
                          ModelMapper mapper,
                          DataSourceEventPublisher publisher) {
        super(dao, mapper, publisher, MapDTO.class, DistanceMap.class);
    }
}
