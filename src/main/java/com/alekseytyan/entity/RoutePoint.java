package com.alekseytyan.entity;

import com.alekseytyan.entity.state.RouteType;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ROUTE_POINT")
public class RoutePoint {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "CITY")
    private City city;

    @OneToMany
    @JoinColumn(name = "LOAD_LOGIWEB")
    private List<Load> load;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private RouteType type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<Load> getLoad() {
        return load;
    }

    public void setLoad(List<Load> load) {
        this.load = load;
    }

    public RouteType getType() {
        return type;
    }

    public void setType(RouteType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoutePoint)) return false;
        RoutePoint that = (RoutePoint) o;
        return id == that.id
                && city.equals(that.city)
                && load.equals(that.load)
                && type == that.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, city, load, type);
    }
}
