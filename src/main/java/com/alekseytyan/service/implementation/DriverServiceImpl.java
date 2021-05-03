package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.DriverDao;
import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.dto.LorryDTO;
import com.alekseytyan.dto.OrderDTO;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.entity.enums.UserRole;
import com.alekseytyan.service.api.DriverService;
import com.alekseytyan.service.api.OrderService;
import com.alekseytyan.util.date.DateChecker;
import com.alekseytyan.util.pathfinding.Route;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DriverServiceImpl extends AbstractServiceImpl<Driver, DriverDao, DriverDTO, Long> implements DriverService {

    private final PasswordEncoder passwordEncoder;

    private final OrderService orderService;

    @Autowired
    public DriverServiceImpl(DriverDao dao,
                             ModelMapper mapper,
                             PasswordEncoder passwordEncoder,
                             OrderService orderService) {

        super(dao, mapper, DriverDTO.class, Driver.class);

        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
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
    public DriverDTO save(DriverDTO driverDTO) {

        // Encrypt password in service method.
        // Protected from changing on frontend side
        driverDTO.getUser().setPassword(passwordEncoder.encode(driverDTO.getUser().getPassword()));

        // Set role
        driverDTO.getUser().setRole(UserRole.ROLE_DRIVER);

        // Set account activation (Required by Spring Security)
        driverDTO.getUser().setEnabled(true);

        // call super method to save DTO with prepared properties
        return super.save(driverDTO);
    }

    @Override
    public DriverDTO update(DriverDTO driverDTO) {

        // Set role
        driverDTO.getUser().setRole(UserRole.ROLE_DRIVER);

        // Set account activation (Required by Spring Security)
        driverDTO.getUser().setEnabled(true);

        return super.update(driverDTO);
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

        return super.delete(refreshedDriverDTO);
    }

    @Override
    @Transactional
    public DriverDTO deleteById(Long entityId) {

        DriverDTO driverDTO = findById(entityId);

        return delete(driverDTO);
    }
}
