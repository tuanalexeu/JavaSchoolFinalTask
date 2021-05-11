package com.alekseytyan.logiweb.dao.api;

import com.alekseytyan.logiweb.entity.auth.PasswordResetToken;
import com.alekseytyan.logiweb.entity.auth.User;

public interface PasswordResetTokenDao extends AbstractDao<PasswordResetToken, Long> {
    void createPasswordResetToken(User user, String token);
    PasswordResetToken findByToken(String token);
}
