package com.alekseytyan.logiweb.service.implementation;

import com.alekseytyan.logiweb.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.dao.api.UserDao;
import com.alekseytyan.logiweb.entity.User;
import com.alekseytyan.logiweb.service.api.UserService;
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
                           PasswordEncoder passwordEncoder) {
        super(dao, mapper, UserDTO.class, User.class);

        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findDisabled() {
        return convertToDTO(getDao().findDisabled());
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
    public List<UserDTO> findWithoutDriver() {
        return convertToDTO(getDao().findWithoutDriver());
    }

    private boolean emailExists(String email) {
        return getDao().findById(email) != null;
    }
}
