package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.User;

import java.util.List;

public interface UserDao extends AbstractDao<User, String> {
    List<User> findDisabled();
    List<User> findWithoutDriver();

    List<User> findDisabledAndVerified();

    void deleteIfUnconfirmed(String email);
}
