package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.aspect.CrudAnnotation;
import com.alekseytyan.logiweb.dto.LorryDTO;
import com.alekseytyan.logiweb.dto.LorryStatsDTO;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.dao.api.LorryDao;
import com.alekseytyan.logiweb.entity.Lorry;
import com.alekseytyan.logiweb.service.api.LorryService;
import com.alekseytyan.logiweb.service.api.OrderService;
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
                            ModelMapper mapper) {
        super(dao, mapper, LorryDTO.class, Lorry.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LorryDTO> findSuitableLorries(OrderDTO orderDTO) {

        int weight = orderService.calculateWeight(orderService.convertToEntity(orderDTO));

        return convertToDTO(getDao().findSuitableLorries(weight));
    }

    @Override
    @Transactional(readOnly = true)
    public LorryStatsDTO getStatistics() {
        LorryStatsDTO lorryStatsDTO = new LorryStatsDTO();

        lorryStatsDTO.setAvailable(getDao().countAvailable());
        lorryStatsDTO.setUnavailable(getDao().countUnavailable());
        lorryStatsDTO.setBroken(getDao().countBroken());

        return lorryStatsDTO;
    }

    @Override
    @Transactional
    @CrudAnnotation(code = "truck")
    public LorryDTO save(LorryDTO lorryDTO) {
        return super.save(lorryDTO);
    }

    @Override
    @Transactional
    @CrudAnnotation(code = "truck")
    public LorryDTO update(LorryDTO lorryDTO) {
        return super.update(lorryDTO);
    }

    @Override
    @Transactional
    @CrudAnnotation(code = "truck")
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

        return super.delete(refreshedLorryDTO);
    }

    @Override
    @Transactional
    public LorryDTO deleteById(String entityId) {

        LorryDTO lorryDTO = findById(entityId);

        return delete(lorryDTO);
    }
}
