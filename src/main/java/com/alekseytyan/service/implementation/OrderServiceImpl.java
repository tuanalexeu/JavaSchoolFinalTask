package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.OrderDao;
import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.City;
import com.alekseytyan.entity.DistanceMap;
import com.alekseytyan.entity.Load;
import com.alekseytyan.entity.Order;
import com.alekseytyan.service.api.CityService;
import com.alekseytyan.service.api.LoadService;
import com.alekseytyan.service.api.MapService;
import com.alekseytyan.service.api.OrderService;
import com.alekseytyan.util.Route;
import com.alekseytyan.util.RouteChecker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderDao, OrderDTO, Long> implements OrderService {

    private final MapService mapService;
    private final CityService cityService;
    private final LoadService loadService;

    @Autowired
    public OrderServiceImpl(OrderDao dao, ModelMapper mapper,
                            MapService mapService,
                            CityService cityService,
                            LoadService loadService) {
        super(dao, mapper, OrderDTO.class, Order.class);

        this.mapService = mapService;
        this.cityService = cityService;
        this.loadService = loadService;
    }

    @Override
    @Transactional
    public OrderDTO delete(OrderDTO orderDTO) {

        if(orderDTO.getDrivers() != null) {
            // Set order as null in dependencies
            for (DriverDTO d: orderDTO.getDrivers()) {
                d.setOrder(null);
            }
        }

        if(orderDTO.getLorry() != null) {
            orderDTO.getLorry().setOrder(null);
        }

        OrderDTO refreshedOrderDTO = update(orderDTO);

        return super.delete(refreshedOrderDTO);
    }

    @Override
    @Transactional
    public OrderDTO deleteById(Long entityId) {

        OrderDTO orderDTO = findById(entityId);

        return delete(orderDTO);
    }

    @Override
    public Route calculateRoute(OrderDTO orderDTO) {

        List<Load> loads = loadService.convertToEntity(orderDTO.getLoads());
        List<DistanceMap> distanceMaps = mapService.convertToEntity(mapService.findAll());
        City cityStart = cityService.convertToEntity(orderDTO.getLorry().getCity());

        RouteChecker routeChecker = new RouteChecker(loads, distanceMaps, cityStart);
        
        return routeChecker.calculateRoute();
    }
}
