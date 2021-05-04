package com.alekseytyan.dto;

import com.alekseytyan.entity.enums.UserRole;
import com.alekseytyan.validation.api.PasswordMatches;
import com.alekseytyan.validation.api.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@PasswordMatches
public class UserDTO {

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    private String password;

    private String matchingPassword;

    private String firstName;

    private String lastName;

    private UserRole role;

    private DriverDTO driver;

    private boolean enabled;
}
