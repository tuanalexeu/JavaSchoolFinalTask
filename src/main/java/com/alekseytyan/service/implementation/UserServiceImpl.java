package com.alekseytyan.service.implementation;

import com.alekseytyan.controller.auth.exception.UserAlreadyExistException;
import com.alekseytyan.dao.api.UserDao;
import com.alekseytyan.dto.UserDTO;
import com.alekseytyan.entity.User;
import com.alekseytyan.listener.DataSourceEventPublisher;
import com.alekseytyan.service.api.UserService;
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

    @Autowired
    public UserServiceImpl(UserDao dao,
                           ModelMapper mapper,
                           PasswordEncoder passwordEncoder,
                           DataSourceEventPublisher publisher) {
        super(dao, mapper, publisher, UserDTO.class, User.class);

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findDisabled() {
        return convertToDTO(getDao().findDisabled());
    }

    @Override
    public User registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException {
        if (emailExists(userDto.getEmail())) {
            throw new UserAlreadyExistException("There is an account with that email address: "
                    + userDto.getEmail());
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());

        return getDao().save(user);
    }

    private boolean emailExists(String email) {
        return getDao().findById(email) != null;
    }
}
