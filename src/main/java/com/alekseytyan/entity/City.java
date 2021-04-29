package com.alekseytyan.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CITY")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "City.findAllNames",
                    query = "select name from City")
})
public class City {
    @Id
    @Column(name = "NAME", length = 30, nullable = false)
    @Size(min = 2, max = 30)
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
