package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.LoadDao;
import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.entity.Load;
import com.alekseytyan.service.api.LoadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadServiceImpl extends AbstractServiceImpl<Load, LoadDao, LoadDTO, Long> implements LoadService {

    @Autowired
    public LoadServiceImpl(LoadDao dao, ModelMapper mapper) {
        super(dao, mapper, LoadDTO.class, Load.class);
    }

    @Override
    public List<LoadDTO> findByOrderId(Long orderId) {
        return convertToDTO(getDao().findByOrderId(orderId));
    }
}
