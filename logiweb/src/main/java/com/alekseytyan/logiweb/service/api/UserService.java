package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.exception.UserAlreadyExistException;
import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.security.User;

import java.util.List;

public interface UserService extends AbstractService<User, UserDTO, String> {
    List<UserDTO> findDisabled(int size, int page);
    List<UserDTO> findDisabledAndVerified(int size, int page);
    UserDTO registerNewUserAccount(UserDTO userDto) throws UserAlreadyExistException;
    List<UserDTO> findWithoutDriver(int size, int page);

    void deleteIfUnconfirmed(String email);
}
