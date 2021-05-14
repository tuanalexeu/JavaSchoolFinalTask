package com.alekseytyan.logiweb.dto;

import com.alekseytyan.logiweb.validation.api.ValidPassword;
import lombok.Data;

import java.io.Serializable;

@Data
public class PasswordDTO implements Serializable {

    private String oldPassword;

    private String token;

    @ValidPassword
    private String newPassword;
}