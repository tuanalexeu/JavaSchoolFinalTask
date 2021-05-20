package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.security.User;

import java.util.List;

public interface UserService extends AbstractService<User, UserDTO, String> {
    List<UserDTO> findDisabled(Integer size, Integer page);
    List<UserDTO> findDisabledAndVerified(Integer size, Integer page);
    UserDTO registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;
    List<UserDTO> findWithoutDriver(Integer size, Integer page);

    void deleteIfUnconfirmed(String email);
}
