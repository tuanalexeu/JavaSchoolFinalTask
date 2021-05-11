package com.alekseytyan.logiweb.dto;

import com.alekseytyan.logiweb.entity.enums.UserRole;
import com.alekseytyan.logiweb.validation.api.PasswordMatches;
import com.alekseytyan.logiweb.validation.api.ValidEmail;
import com.alekseytyan.logiweb.validation.api.ValidPassword;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Data
@PasswordMatches
public class UserDTO {

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @ValidPassword
    private String password;

    private String matchingPassword;

    private String firstName;

    private String lastName;

    private Collection<RoleDTO> roles;

    private UserRole role;

    private DriverDTO driver;

    private boolean enabled;

    private boolean emailConfirmed;
}
