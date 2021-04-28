package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.LorryDao;
import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Lorry;
import com.alekseytyan.service.api.LorryService;
import com.alekseytyan.service.api.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LorryServiceImpl extends AbstractServiceImpl<Lorry, LorryDao, LorryDTO, String> implements LorryService {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public LorryServiceImpl(LorryDao dao, ModelMapper mapper) {
        super(dao, mapper, LorryDTO.class, Lorry.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LorryDTO> findSuitableLorries(Long weight) {
        return convertToDTO(getDao().findSuitableLorries(weight));
    }

    @Override
    @Transactional
    public LorryDTO delete(LorryDTO lorryDTO) {

        // Set order as null in dependencies
        OrderDTO orderDTO = orderService.findById(lorryDTO.getOrder().getId());

        orderDTO.setLorry(null);
        orderDTO.getDrivers().forEach(d -> d.setLorry(null));

        orderService.update(orderDTO);

        lorryDTO.setOrder(null);


        LorryDTO refreshedLorryDTO = update(lorryDTO);

        return super.delete(refreshedLorryDTO);
    }

    @Override
    @Transactional
    public LorryDTO deleteById(String entityId) {

        LorryDTO lorryDTO = findById(entityId);

        return delete(lorryDTO);
    }
}
