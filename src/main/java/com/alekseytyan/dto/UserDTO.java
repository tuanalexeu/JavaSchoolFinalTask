package com.alekseytyan.dto;

import com.alekseytyan.entity.Driver;
import com.alekseytyan.entity.User;
import lombok.Data;

@Data
public class UserDTO {

    private String email;

    private String username;

    private String password;

    private User.UserRole role;

    private Driver driver;
}
