package com.alekseytyan.logiweb.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@Data
public class RoleDTO {
    private Long id;

    private String name;

    @ToString.Exclude
    private Collection<UserDTO> users;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<PrivilegeDTO> privileges;
}
