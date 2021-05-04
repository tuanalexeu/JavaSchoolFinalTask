package com.alekseytyan.dto;

import com.alekseytyan.entity.enums.UserRole;
import lombok.Data;

@Data
public class UserDTO {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private UserRole role;

    private DriverDTO driver;

    private boolean enabled;
}
