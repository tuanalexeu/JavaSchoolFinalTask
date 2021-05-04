package com.alekseytyan.service.api;

import com.alekseytyan.dto.UserDTO;
import com.alekseytyan.entity.User;

import java.util.List;

public interface UserService extends AbstractService<User, UserDTO, String> {
    List<UserDTO> findDisabled();
}
