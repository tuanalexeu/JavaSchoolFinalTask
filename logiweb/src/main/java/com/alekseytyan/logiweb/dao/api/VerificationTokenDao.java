package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.User;
import com.alekseytyan.logiweb.entity.VerificationToken;

public interface VerificationTokenDao extends AbstractDao<VerificationToken, Long> {
    void createVerificationToken(User user, String token);
    VerificationToken getVerificationToken(String token);
}
