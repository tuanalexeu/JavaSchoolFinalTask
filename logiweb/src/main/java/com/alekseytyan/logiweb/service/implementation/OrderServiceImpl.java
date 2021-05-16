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

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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

        List<OrderDTO> orderDTOList = convertToDTO(getDao().findVerified());
        List<Route> routes = calculateRoute(orderDTOList);

        int route_index = 0;

        Iterator<OrderDTO> iterator = orderDTOList.listIterator();

        while (iterator.hasNext()) {
            iterator.next();
            if (!routes.get(route_index).isPossible()) {
                iterator.remove();
                route_index++;
            }
        }

        return orderDTOList;
    }

    @Override
    public Route calculateRoute(OrderDTO orderDTO) {

        List<DistanceMap> distanceMaps = mapService.convertToEntity(mapService.findAll());

        return RouteChecker.calculateRoute(distanceMaps, convertToEntity(orderDTO).getLoads());
    }

    @Override
    public List<Route> calculateRoute(List<OrderDTO> orderDTOList) {
        List<DistanceMap> distanceMaps = mapService.convertToEntity(mapService.findAll());

        return convertToEntity(orderDTOList)
                .stream()
                .map(o -> RouteChecker.calculateRoute(distanceMaps, o.getLoads()))
                .collect(Collectors.toList());
    }

    @Override
    public int calculateWeight(Order order) {
        List<Load> loads = order.getLoads();

        return RouteChecker.calculateMaxWeight(loads);
    }


}
