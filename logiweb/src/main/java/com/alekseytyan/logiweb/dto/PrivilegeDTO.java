package com.alekseytyan.logiweb.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Data
public class PrivilegeDTO implements Serializable {

    private Long id;
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<RoleDTO> roles;
}
