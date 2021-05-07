package com.alekseytyan.service.implementation;

import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.LorryStatsDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.listener.DataSourceEventPublisher;
import com.alekseytyan.dao.api.LorryDao;
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
    public LorryServiceImpl(LorryDao dao,
                            ModelMapper mapper,
                            DataSourceEventPublisher publisher) {
        super(dao, mapper, publisher, LorryDTO.class, Lorry.class);
    }

    @Override
    public LorryDTO save(LorryDTO lorryDTO) {

        getPublisher().publishEvent("truck");

        return super.save(lorryDTO);
    }

    @Override
    public LorryDTO update(LorryDTO lorryDTO) {

        getPublisher().publishEvent("truck");

        return super.update(lorryDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LorryDTO> findSuitableLorries(OrderDTO orderDTO) {

        int weight = orderService.calculateWeight(orderService.convertToEntity(orderDTO));

        return convertToDTO(getDao().findSuitableLorries(weight));
    }

    @Override
    public LorryStatsDTO getStatistics() {
        LorryStatsDTO lorryStatsDTO = new LorryStatsDTO();

        lorryStatsDTO.setAvailable(getDao().countAvailable());
        lorryStatsDTO.setUnavailable(getDao().countUnavailable());
        lorryStatsDTO.setBroken(getDao().countBroken());

        return lorryStatsDTO;
    }

    @Override
    @Transactional
    public LorryDTO delete(LorryDTO lorryDTO) {

        if(lorryDTO.getOrder() != null) {
            // Set order as null in dependencies
            OrderDTO orderDTO = orderService.findById(lorryDTO.getOrder().getId());

            if(orderDTO.getDrivers() != null) {
                orderDTO.getDrivers().forEach(d -> d.setLorry(null));
            }

            orderService.delete(orderDTO);
        }

        lorryDTO.setOrder(null);


        LorryDTO refreshedLorryDTO = update(lorryDTO);

        getPublisher().publishEvent("truck");

        return super.delete(refreshedLorryDTO);
    }

    @Override
    @Transactional
    public LorryDTO deleteById(String entityId) {

        LorryDTO lorryDTO = findById(entityId);

        return delete(lorryDTO);
    }
}
