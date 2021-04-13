package com.alekseytyan.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Objects;

@Entity
@Table(name = "MAP")
public class Map {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FROM_CITY")
    private City from;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TO_CITY")
    private City to;

    @Column(name = "DISTANCE")
    @Min(0)
    private int distance;

    public City getFrom() {
        return from;
    }

    public void setFrom(City from) {
        this.from = from;
    }

    public City getTo() {
        return to;
    }

    public void setTo(City to) {
        this.to = to;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Map)) return false;
        Map map = (Map) o;
        return id == map.id
                && distance == map.distance
                && from.equals(map.from)
                && to.equals(map.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, from, to, distance);
    }
}
