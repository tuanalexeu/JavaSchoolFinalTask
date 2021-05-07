package com.alekseytyan.dao.api;

import com.alekseytyan.entity.User;

import java.util.List;

public interface UserDao extends AbstractDao<User, String> {
    List<User> findDisabled();
}
