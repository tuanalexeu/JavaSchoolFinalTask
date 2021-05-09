package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.aspect.CrudAnnotation;
import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.dto.OrderDTO;
import com.alekseytyan.logiweb.util.pathfinding.Route;
import com.alekseytyan.logiweb.util.pathfinding.RouteChecker;
import com.alekseytyan.logiweb.dao.api.OrderDao;
import com.alekseytyan.logiweb.entity.City;
import com.alekseytyan.logiweb.entity.DistanceMap;
import com.alekseytyan.logiweb.entity.Load;
import com.alekseytyan.logiweb.entity.Order;
import com.alekseytyan.logiweb.service.api.MapService;
import com.alekseytyan.logiweb.service.api.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl extends AbstractServiceImpl<Order, OrderDao, OrderDTO, Long> implements OrderService {

    private final MapService mapService;

    @Autowired
    public OrderServiceImpl(OrderDao dao,
                            ModelMapper mapper,
                            MapService mapService) {
        super(dao, mapper, OrderDTO.class, Order.class);

        this.mapService = mapService;
    }

    @Override
    @Transactional
    @CrudAnnotation(code = "order")
    public OrderDTO save(OrderDTO orderDTO) {
        return super.save(orderDTO);
    }

    @Override
    @Transactional
    @CrudAnnotation(code = "order")
    public OrderDTO update(OrderDTO orderDTO) {
        return super.update(orderDTO);
    }

    @Override
    @Transactional
    @CrudAnnotation(code = "order")
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
