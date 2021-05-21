package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.dto.VerificationTokenDTO;
import com.alekseytyan.logiweb.entity.auth.VerificationToken;

/**
 * Service is used to handle verification tokens
 */
public interface VerificationService extends AbstractService<VerificationToken, VerificationTokenDTO, Long> {
    void createVerificationToken(UserDTO userDTO, String token);
    VerificationTokenDTO getVerificationToken(String token);
    void deleteAll();
}
