package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.entity.User;

import java.util.List;

public interface UserDao extends AbstractDao<User, String> {
    List<User> findDisabled();
    List<User> findWithoutDriver();
}
