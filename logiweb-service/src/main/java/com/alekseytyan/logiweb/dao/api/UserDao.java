package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.User;

import java.util.List;

/**
 * Data access object to work with User table
 */
public interface UserDao extends AbstractDao<User, String> {

    /**
     * Finds disabled users
     */
    List<User> findDisabled(Integer size, Integer page);

    /**
     * Finds users with no driver assigned
     */
    List<User> findWithoutDriver(Integer size, Integer page);

    /**
     * Finds disabled and verified users
     */
    List<User> findDisabledAndVerified(Integer size, Integer page);

    /**
     * Deletes unconfirmed users. Is used in daily-cleaning-task
     */
    void deleteIfUnconfirmed(String email);
}
