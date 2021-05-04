package com.alekseytyan.entity;

        import com.alekseytyan.entity.enums.UserRole;
        import lombok.*;

        import javax.persistence.*;
        import javax.validation.constraints.Pattern;

@Entity
@Table(name = "USER_LOGIWEB")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "User.findDisabled",
                query = "select u from User u where u.enabled = FALSE")
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

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToOne(mappedBy = "user")
    private Driver driver;

    @Column(name = "ENABLED", columnDefinition = "boolean default false")
    private boolean enabled;
}
