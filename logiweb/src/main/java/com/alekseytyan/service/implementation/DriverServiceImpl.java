package com.alekseytyan.service.implementation;

import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.DriverStatsDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.listener.DataSourceEventPublisher;
import com.alekseytyan.util.pathfinding.Route;
import com.alekseytyan.dao.api.DriverDao;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.OrderService;
import com.alekseytyan.util.date.DateChecker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverServiceImpl extends AbstractServiceImpl<Driver, DriverDao, DriverDTO, Long> implements DriverService {

    private final OrderService orderService;

    @Autowired
    public DriverServiceImpl(DriverDao dao,
                             ModelMapper mapper,
                             OrderService orderService,
                             DataSourceEventPublisher publisher) {

        super(dao, mapper, publisher, DriverDTO.class, Driver.class);

        this.orderService = orderService;
    }

    @Override
    public DriverDTO save(DriverDTO driverDTO) {

        getPublisher().publishEvent("driver");

        return super.save(driverDTO);
    }

    @Override
    public DriverDTO update(DriverDTO driverDTO) {

        getPublisher().publishEvent("driver");

        return super.update(driverDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public DriverDTO findDriverByUser(String email) {
        return convertToDTO(getDao().findDriverByUser(email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverDTO> findCoDrivers(Long orderId) {
        return convertToDTO(getDao().findCoDrivers(orderId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DriverDTO> findSuitableDrivers(OrderDTO orderDTO, Route route, LorryDTO lorryDTO) {
        String cityName = lorryDTO.getCity().getName();

        DateChecker dateChecker;
        if(route.isPossible()) {
            dateChecker = DateChecker.calculateHoursInMonth(route.getTime());
            return convertToDTO(getDao().findSuitableDrivers(cityName, dateChecker.getHours()));
        }

        return convertToDTO(getDao().findSuitableDrivers(cityName, 177));
    }

    @Override
    public List<DriverDTO> findWithoutUser() {
        return convertToDTO(getDao().findWithoutUser());
    }

    @Override
    public DriverStatsDTO getStatistics() {
        DriverStatsDTO driverStatsDTO = new DriverStatsDTO();

        driverStatsDTO.setAvailable(getDao().countAvailable());
        driverStatsDTO.setUnavailable(getDao().countUnavailable());

        return driverStatsDTO;
    }

    @Override
    @Transactional
    public DriverDTO delete(DriverDTO driverDTO) {

        if(driverDTO.getOrder() != null) {
            // Set order as null in dependencies
            driverDTO.getOrder().getDrivers().remove(driverDTO);

            if(driverDTO.getOrder().getDrivers().size() == 0) {
                OrderDTO orderDTO = orderService.findById(driverDTO.getOrder().getId());

                orderService.delete(orderDTO);
            }
        }

        DriverDTO refreshedDriverDTO = update(driverDTO);

        getPublisher().publishEvent("driver");

        return super.delete(refreshedDriverDTO);
    }

    @Override
    @Transactional
    public DriverDTO deleteById(Long entityId) {

        DriverDTO driverDTO = findById(entityId);

        return delete(driverDTO);
    }
}
