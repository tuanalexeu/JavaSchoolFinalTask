package com.alekseytyan.infotable.data;

import com.alekseytyan.infotable.data.enums.UserRole;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UserDTO {

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
