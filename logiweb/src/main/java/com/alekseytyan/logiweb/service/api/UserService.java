package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.controller.auth.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.User;

import java.util.List;

public interface UserService extends AbstractService<User, UserDTO, String> {
    List<UserDTO> findDisabled();
    UserDTO registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;
    List<UserDTO> findWithoutDriver();
}
