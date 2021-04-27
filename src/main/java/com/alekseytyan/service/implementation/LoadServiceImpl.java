package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.LoadDao;
import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.entity.Load;
import com.alekseytyan.service.api.LoadService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadServiceImpl extends AbstractServiceImpl<Load, LoadDao, LoadDTO, Long> implements LoadService {

    public LoadServiceImpl(LoadDao dao, ModelMapper mapper) {
        super(dao, mapper, LoadDTO.class, Load.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findOrderId(Long loadId) {
        return getDao().findById(loadId).getOrder().getId();
    }

    @Override
    public LoadDTO delete(LoadDTO loadDTO) {

        loadDTO.getOrder().getLoads().remove(loadDTO);

        LoadDTO refreshedLoadDTO = update(loadDTO);

        return super.delete(refreshedLoadDTO);
    }

    @Override
    public LoadDTO deleteById(Long entityId) {

        LoadDTO loadDTO = findById(entityId);

        return delete(loadDTO);
    }
}
