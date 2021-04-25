package com.alekseytyan.entity;

import com.alekseytyan.entity.enums.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "USER_LOGIWEB")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @Column(name = "EMAIL", length = 60)
    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user")
    private Driver driver;

    @Column(name = "ENABLED", columnDefinition = "boolean default true")
    private boolean enabled;
}
