package com.alekseytyan.logiweb.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.Collection;

@Data
public class PrivilegeDTO {

    private Long id;
    private String name;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<RoleDTO> roles;
}
