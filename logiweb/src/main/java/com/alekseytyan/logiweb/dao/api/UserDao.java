package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.User;

import java.util.List;

public interface UserDao extends AbstractDao<User, String> {
    List<User> findDisabled(int size, int page);
    List<User> findWithoutDriver(int size, int page);

    List<User> findDisabledAndVerified(int size, int page);

    void deleteIfUnconfirmed(String email);
}
