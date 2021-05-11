package com.alekseytyan.logiweb.dto;

import com.alekseytyan.logiweb.validation.api.ValidPassword;
import lombok.Data;

@Data
public class PasswordDTO {

    private String oldPassword;

    private String token;

    @ValidPassword
    private String newPassword;
}