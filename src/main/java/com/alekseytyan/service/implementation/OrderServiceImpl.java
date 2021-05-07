package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.OrderDao;
import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LoadDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.City;
import com.alekseytyan.entity.DistanceMap;
import com.alekseytyan.entity.Load;
import com.alekseytyan.entity.Order;
import com.alekseytyan.entity.enums.LoadStatus;
import com.alekseytyan.listener.DataSourceEventPublisher;
import com.alekseytyan.service.api.MapService;
import com.alekseytyan.service.api.OrderService;
import com.alekseytyan.util.pathfinding.Route;
import com.alekseytyan.util.pathfinding.RouteChecker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderDao, OrderDTO, Long> implements OrderService {

    private final MapService mapService;

    @Autowired
    public OrderServiceImpl(OrderDao dao,
                            ModelMapper mapper,
                            MapService mapService,
                            DataSourceEventPublisher publisher) {
        super(dao, mapper, publisher, OrderDTO.class, Order.class);

        this.mapService = mapService;
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {

        getPublisher().publishEvent("order");

        return super.save(orderDTO);
    }

    @Override
    public OrderDTO update(OrderDTO orderDTO) {

        getPublisher().publishEvent("order");

        return super.update(orderDTO);
    }

    @Override
    @Transactional
    public OrderDTO delete(OrderDTO orderDTO) {

        if(orderDTO.getDrivers() != null) {
            // Set order as null in dependencies
            for (DriverDTO d: orderDTO.getDrivers()) {
                d.setOrder(null);
                d.setLorry(null);
            }
        }

        if(orderDTO.getLorry() != null) {
            orderDTO.getLorry().setOrder(null);
        }

        OrderDTO refreshedOrderDTO = update(orderDTO);

        getPublisher().publishEvent("order");

        return super.delete(refreshedOrderDTO);
    }

    @Override
    @Transactional
    public OrderDTO deleteById(Long entityId) {

        OrderDTO orderDTO = findById(entityId);

        return delete(orderDTO);
    }

    @Override
    public List<OrderDTO> findVerified() {
        return convertToDTO(getDao().findVerified());
    }

    @Override
    public Route calculateRoute(OrderDTO orderDTO) {

        List<DistanceMap> distanceMaps = mapService.convertToEntity(mapService.findAll());

        Order order = convertToEntity(orderDTO);

        List<Load> loads = order.getLoads();

        if(orderDTO.getLorry() != null) {
            City cityStart = order.getLorry().getCity();
            return RouteChecker.calculateRoute(distanceMaps, loads, cityStart);
        } else {
            return RouteChecker.calculateRoute(distanceMaps, loads);
        }
    }

    @Override
    public List<Route> calculateRoute(List<OrderDTO> orderDTOList) {


        List<DistanceMap> distanceMaps = mapService.convertToEntity(mapService.findAll());
        List<Route> routes = new ArrayList<>();


        List<Order> orders = convertToEntity(orderDTOList);

        for (Order o: orders) {
            List<Load> loads = o.getLoads();
            City cityStart = o.getLorry().getCity();

            routes.add(RouteChecker.calculateRoute(distanceMaps, loads, cityStart));
        }

        return routes;
    }

    @Override
    public int calculateWeight(Order order) {
        List<Load> loads = order.getLoads();

        return RouteChecker.calculateMaxWeight(loads);
    }


}
