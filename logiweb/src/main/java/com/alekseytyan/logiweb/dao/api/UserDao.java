package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.User;

import java.util.List;

public interface UserDao extends AbstractDao<User, String> {
    List<User> findDisabled(Integer size, Integer page);
    List<User> findWithoutDriver(Integer size, Integer page);
    List<User> findDisabledAndVerified(Integer size, Integer page);
    void deleteIfUnconfirmed(String email);
}
