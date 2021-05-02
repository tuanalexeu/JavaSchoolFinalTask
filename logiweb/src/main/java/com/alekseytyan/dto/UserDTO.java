package com.alekseytyan.dto;

import com.alekseytyan.entity.enums.UserRole;
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
