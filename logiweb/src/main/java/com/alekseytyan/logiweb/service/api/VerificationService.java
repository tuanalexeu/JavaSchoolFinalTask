package com.alekseytyan.logiweb.service.api;

import com.alekseytyan.logiweb.dto.UserDTO;
import com.alekseytyan.logiweb.dto.VerificationTokenDTO;
import com.alekseytyan.logiweb.entity.VerificationToken;

public interface VerificationService extends AbstractService<VerificationToken, VerificationTokenDTO, Long> {
    void createVerificationToken(UserDTO userDTO, String token);
    VerificationTokenDTO getVerificationToken(String token);
}
