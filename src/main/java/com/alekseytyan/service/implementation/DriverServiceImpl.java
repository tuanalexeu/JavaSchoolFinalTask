package com.alekseytyan.service.implementation;

import com.alekseytyan.dao.api.DriverDao;
import com.alekseytyan.dto.DriverDTO;
import com.alekseytyan.entity.Driver;
import com.alekseytyan.entity.enums.UserRole;
import com.alekseytyan.service.api.DriverService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sun.security.util.Password;

import java.util.List;

@Service
public class DriverServiceImpl extends AbstractServiceImpl<Driver, DriverDao, DriverDTO, Long> implements DriverService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DriverServiceImpl(DriverDao dao,
                             ModelMapper mapper,
                             PasswordEncoder passwordEncoder) {

        super(dao, mapper, DriverDTO.class, Driver.class);

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public DriverDTO findDriverByUser(String email) {
        return convertToDTO(getDao().findDriverByUser(email));
    }

    @Override
    public List<DriverDTO> findCoDrivers(Long orderId) {
        return convertToDTO(getDao().findCoDrivers(orderId));
    }

    @Override
    public void save(DriverDTO driverDTO) {

        // Encrypt password in service method.
        // Protected from changing on frontend side
        driverDTO.getUser().setPassword(passwordEncoder.encode(driverDTO.getUser().getPassword()));

        // Set role
        driverDTO.getUser().setRole(UserRole.ROLE_DRIVER);

        // Set account activation (Required by Spring Security)
        driverDTO.getUser().setEnabled(true);

        // call super method to save DTO with prepared properties
        super.save(driverDTO);
    }

    @Override
    public void update(DriverDTO driverDTO) {

        // Encrypt password in service method.
        // Protected from changing on frontend side
        driverDTO.getUser().setPassword(passwordEncoder.encode(driverDTO.getUser().getPassword()));

        // Set role
        driverDTO.getUser().setRole(UserRole.ROLE_DRIVER);

        // Set account activation (Required by Spring Security)
        driverDTO.getUser().setEnabled(true);

        super.update(driverDTO);
    }
}
