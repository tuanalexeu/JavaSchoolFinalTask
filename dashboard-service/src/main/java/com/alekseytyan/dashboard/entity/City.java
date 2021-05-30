package com.alekseytyan.dashboard.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "CITY")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
