package com.alekseytyan.entity;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDER")
public class Order implements Comparable<Order> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "IS_FINISHED")
    private boolean isFinished;

    @JoinColumn(name = "ROUTE_POINT", referencedColumnName = "ID")
    @ManyToMany
    private List<RoutePoint> routePoints;

    @OneToOne
    @JoinColumn(name = "TRUCK", referencedColumnName = "ID")
    private Truck truck;

    @OneToMany
    @JoinColumn(name = "DRIVERS", referencedColumnName = "ID")
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

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
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
                && truck.equals(order.truck)
                && drivers.equals(order.drivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isFinished, routePoints, truck, drivers);
    }

    @Override
    public int compareTo(Order o) {
        return Comparator
                .comparing(Order::getId)
                .compare(this, o);
    }
}
