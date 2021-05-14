package com.alekseytyan.logiweb.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Date;

@Data
public class VerificationTokenDTO implements Serializable {

    private Long id;
    private String token;
    private Date expiryDate;

    @ToString.Exclude
    private UserDTO user;

}
