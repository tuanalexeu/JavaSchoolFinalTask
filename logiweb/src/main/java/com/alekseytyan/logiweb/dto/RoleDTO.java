package com.alekseytyan.logiweb.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Data
public class RoleDTO implements Serializable {
    private Long id;

    private String name;

    @ToString.Exclude
    private Collection<UserDTO> users;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<PrivilegeDTO> privileges;
}
