package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.auth.PasswordResetToken;
import com.alekseytyan.logiweb.entity.security.User;

/**
 * Data access object to work with PasswordResetToken table
 */
public interface PasswordResetTokenDao extends AbstractDao<PasswordResetToken, Long> {

    /**
     * Creates new token
     * @param user - user, which the token is created for
     * @param token - token as a string
     */
    void createPasswordResetToken(User user, String token);

    /**
     * Finds token by string representation of token
     * @param token - string
     * @return - needed token
     */
    PasswordResetToken findByToken(String token);
}
