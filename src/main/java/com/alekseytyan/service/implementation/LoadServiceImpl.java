package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.LoadDao;
import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.entity.Load;
import com.alekseytyan.service.api.LoadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadServiceImpl extends AbstractServiceImpl<Load, LoadDao, LoadDTO> implements LoadService {

    @Autowired
    public LoadServiceImpl(LoadDao dao, ModelMapper mapper) {
        super(dao, mapper);
    }

    @Override
    public LoadDTO convertToDTO(Load entity) {
        return getMapper().map(entity, LoadDTO.class);
    }
}
