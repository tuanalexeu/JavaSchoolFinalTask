package com.alekseytyan.logiweb.entity.security;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "PRIVILEGE_LOGIWEB")
@Getter @Setter
@NamedQueries({
        @NamedQuery(name = "Privilege.findByName",
                query = "SELECT p FROM Privilege p where p.name = :name")
})
@NoArgsConstructor
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;

    public Privilege(String name) {
        this.name = name;
    }
}