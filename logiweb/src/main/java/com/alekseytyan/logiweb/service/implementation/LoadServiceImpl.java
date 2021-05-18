package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.dto.LoadDTO;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.dao.api.LoadDao;
import com.alekseytyan.logiweb.entity.Load;
import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import com.alekseytyan.logiweb.service.api.LoadService;
import com.alekseytyan.logiweb.service.api.OrderService;
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
                           ModelMapper mapper) {
        super(dao, mapper, LoadDTO.class, Load.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Long findOrderId(Long loadId) {
        return getDao().findById(loadId).getOrder().getId();
    }

    @Override
    @Transactional
    public LoadDTO update(LoadDTO loadDTO) {

        OrderDTO orderDTO = loadDTO.getOrder();

        boolean isOrderFinished = orderDTO.getLoads().stream().allMatch(l -> l.getStatus() == LoadStatus.DELIVERED);
        orderDTO.setFinished(isOrderFinished);
        orderService.update(orderDTO);

        return super.update(loadDTO);
    }

    @Override
    @Transactional
    public LoadDTO delete(LoadDTO loadDTO) {

        OrderDTO orderDTO = loadDTO.getOrder();
        orderDTO.getLoads().remove(loadDTO);

        loadDTO.setOrder(orderService.update(orderDTO));

        LoadDTO refreshedLoadDTO = update(loadDTO);

        return super.delete(refreshedLoadDTO);
    }

    @Override
    @Transactional
    public LoadDTO deleteById(Long entityId) {

        LoadDTO loadDTO = findById(entityId);

        return delete(loadDTO);
    }
}
