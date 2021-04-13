package com.alekseytyan.entity;

import javax.persistence.*;

@Entity
@Table(name = "ROUTE_POINT")
public class RoutePoint {

    public enum RouteType {
        LOAD, UNLOAD
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "CITY")
    private City city;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private RouteType type;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "LOAD_ID", nullable = false)
    private Load load;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

}
