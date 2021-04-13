package com.alekseytyan.entity;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDER_LOGIWEB")
public class Order implements Comparable<Order> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "IS_FINISHED")
    private boolean isFinished;

    @JoinColumn(name = "ROUTE_POINT")
    @ManyToMany
    private List<RoutePoint> routePoints;

    @OneToOne
    @JoinColumn(name = "LORRY")
    private Lorry lorry;

    @OneToMany
    @JoinColumn(name = "DRIVERS")
    private List<Driver> drivers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public List<RoutePoint> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(List<RoutePoint> routePoint) {
        this.routePoints = routePoint;
    }

    public Lorry getTruck() {
        return lorry;
    }

    public void setTruck(Lorry lorry) {
        this.lorry = lorry;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id
                && isFinished == order.isFinished
                && routePoints.equals(order.routePoints)
                && lorry.equals(order.lorry)
                && drivers.equals(order.drivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isFinished, routePoints, lorry, drivers);
    }

    @Override
    public int compareTo(Order o) {
        return Comparator
                .comparing(Order::getId)
                .compare(this, o);
    }
}
