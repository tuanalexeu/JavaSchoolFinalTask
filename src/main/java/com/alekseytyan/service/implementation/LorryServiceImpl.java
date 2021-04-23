package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.LorryDao;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.entity.Lorry;
import com.alekseytyan.service.api.LorryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LorryServiceImpl extends AbstractServiceImpl<Lorry, LorryDao, LorryDTO, String> implements LorryService {

    @Autowired
    public LorryServiceImpl(LorryDao dao, ModelMapper mapper) {
        super(dao, mapper, LorryDTO.class);
    }

}
