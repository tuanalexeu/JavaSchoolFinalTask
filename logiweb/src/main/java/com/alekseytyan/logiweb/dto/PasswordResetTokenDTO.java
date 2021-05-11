package com.alekseytyan.logiweb.dto;

import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
public class PasswordResetTokenDTO {

    private Long id;
    private String token;

    @ToString.Exclude
    private UserDTO user;

    private Date expiryDate;
}
