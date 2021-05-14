package com.alekseytyan.logiweb.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
public class PasswordResetTokenDTO implements Serializable {

    private Long id;
    private String token;

    @ToString.Exclude
    private UserDTO user;

    private Date expiryDate;
}
