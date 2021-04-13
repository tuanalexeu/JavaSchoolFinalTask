package com.alekseytyan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "CITY")
public class City implements Comparable<City> {

    @Id
    @Column(name = "NAME", length = 30)
    @Size(min = 2, max = 30)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int compareTo(City o) {
        return Comparator
                .comparing(City::getName)
                .compare(this, o);
    }
}
