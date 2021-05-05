package com.alekseytyan.logiweb.dto;

import com.alekseytyan.logiweb.entity.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private String email;

    private String username;

    private String password;

    private UserRole role;

    private DriverDTO driver;

    private boolean enabled;
}
