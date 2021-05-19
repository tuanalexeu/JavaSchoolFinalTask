package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.entity.auth.VerificationToken;

/**
 * Data access object to work with VerificationToken table
 */
public interface VerificationTokenDao extends AbstractDao<VerificationToken, Long> {

    /**
     * Creates new verification token
     * @param user - user, which the token is created for
     * @param token - token as string
     */
    void createVerificationToken(User user, String token);

    /**
     * Finds verification token by its spring representation
     */
    VerificationToken getVerificationToken(String token);

    /**
     * Cleans VerificationToken table
     */
    void deleteAll();
}
