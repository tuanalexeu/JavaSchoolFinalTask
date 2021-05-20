package com.alekseytyan.logiweb.service.implementation.security;

import com.alekseytyan.logiweb.dto.DriverDTO;
import com.alekseytyan.logiweb.entity.Driver;
import com.alekseytyan.logiweb.entity.enums.DriverState;
import com.alekseytyan.logiweb.entity.enums.UserRole;
import com.alekseytyan.logiweb.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.dao.api.UserDao;
import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.service.api.DriverService;
import com.alekseytyan.logiweb.service.api.UserService;
import com.alekseytyan.logiweb.service.implementation.AbstractServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends AbstractServiceImpl<User, UserDao, UserDTO, String> implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final DriverService driverService;


    @Override
    public UserDTO update(UserDTO userDTO) {
        DriverDTO driverDTO = driverService.findDriverByUser(userDTO.getEmail());

        if(driverDTO != null) {
            driverDTO.setUser(null);
            driverService.update(driverDTO);
        }

        if(userDTO.getDriver() != null) {
            userDTO.getDriver().setUser(null);
        }

        return super.update(userDTO);
    }

    @Autowired
    public UserServiceImpl(UserDao dao,
                           ModelMapper mapper,
                           DriverService driverService,
                           PasswordEncoder passwordEncoder) {
        super(dao, mapper, UserDTO.class, User.class);

        this.passwordEncoder = passwordEncoder;
        this.driverService = driverService;
    }

    @Override
    public UserDTO deleteById(String email) {


        DriverDTO driverDTO = driverService.findDriverByUser(email);

        if(driverDTO != null) {
            driverDTO.setUser(null);
            driverService.update(driverDTO);
        }

        return convertToDTO(getDao().deleteById(email));
    }


    @Override
    public List<UserDTO> findDisabled(Integer size, Integer page) {
        return convertToDTO(getDao().findDisabled(size, page));
    }

    @Override
    public List<UserDTO> findDisabledAndVerified(Integer size, Integer page) {
        return convertToDTO(getDao().findDisabledAndVerified(size, page));
    }

    @Override
    public UserDTO registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {

        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        return super.save(userDto);
    }

    @Override
    public List<UserDTO> findWithoutDriver(Integer size, Integer page) {
        return convertToDTO(getDao().findWithoutDriver(size, page));
    }

    @Override
    public void deleteIfUnconfirmed(String email) {
        getDao().deleteIfUnconfirmed(email);
    }

    private boolean emailExists(String email) {
        return getDao().findById(email) != null;
    }
}
