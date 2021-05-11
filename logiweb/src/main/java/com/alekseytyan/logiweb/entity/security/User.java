package com.alekseytyan.logiweb.entity.security;


import com.alekseytyan.logiweb.entity.Driver;
import com.alekseytyan.logiweb.entity.enums.UserRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Collection;

@Entity
@Table(name = "USER_LOGIWEB")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "User.findDisabled",
                query = "select u from User u where u.enabled = FALSE"),
        @NamedQuery(name = "User.findWithoutDriver",
                query = "select u from User u where u.role = 'ROLE_DRIVER'"),
        @NamedQuery(name = "User.findVerifiedAndDisabled",
                query = "select u from User u where u.enabled = FALSE and u.emailConfirmed = TRUE")
})
public class User {

    @Id
    @Column(name = "EMAIL", length = 60)
    @Pattern(regexp = "^([a-zA-Z0-9_\\-.]+)@([a-zA-Z0-9_\\-.]+)\\.([a-zA-Z]{2,5})$")
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "USERS_ROLES",
            joinColumns = @JoinColumn(
                    name = "USER_EMAIL", referencedColumnName = "EMAIL"),
            inverseJoinColumns = @JoinColumn(
                    name = "ROLE_ID", referencedColumnName = "ID"))
    private Collection<Role> roles;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user")
    private Driver driver;

    @Column(name = "ENABLED", columnDefinition = "boolean default false")
    private boolean enabled;

    @Column(name = "EMAIL_CONFIRMED", columnDefinition = "boolean default false")
    private boolean emailConfirmed;
}
