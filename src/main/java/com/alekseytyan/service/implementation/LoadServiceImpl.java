package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.LoadDao;
import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Load;
import com.alekseytyan.listener.DataSourceEventPublisher;
import com.alekseytyan.service.api.LoadService;
import com.alekseytyan.service.api.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoadServiceImpl extends AbstractServiceImpl<Load, LoadDao, LoadDTO, Long> implements LoadService {

    private OrderService orderService;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public LoadServiceImpl(LoadDao dao,
                           ModelMapper mapper,
                           DataSourceEventPublisher publisher) {
        super(dao, mapper, publisher, LoadDTO.class, Load.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findOrderId(Long loadId) {
        return getDao().findById(loadId).getOrder().getId();
    }

    @Override
    public LoadDTO delete(LoadDTO loadDTO) {

        OrderDTO orderDTO = loadDTO.getOrder();
        orderDTO.getLoads().remove(loadDTO);

        loadDTO.setOrder(orderService.update(orderDTO));

        LoadDTO refreshedLoadDTO = update(loadDTO);

        return super.delete(refreshedLoadDTO);
    }

    @Override
    public LoadDTO deleteById(Long entityId) {

        LoadDTO loadDTO = findById(entityId);

        return delete(loadDTO);
    }
}
