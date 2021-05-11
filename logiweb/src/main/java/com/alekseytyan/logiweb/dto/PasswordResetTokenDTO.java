package com.alekseytyan.logiweb.dto;

import com.alekseytyan.logiweb.entity.auth.User;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
public class PasswordResetTokenDTO {

    private Long id;
    private String token;

    @ToString.Exclude
    private User user;

    private Date expiryDate;
}
