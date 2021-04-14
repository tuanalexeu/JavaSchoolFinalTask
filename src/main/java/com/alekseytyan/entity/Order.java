package com.alekseytyan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORDER_LOGIWEB")
@Getter @Setter @NoArgsConstructor
public class Order {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "IS_FINISHED")
    private boolean isFinished;

    @OneToMany
    @JoinColumn(name = "ROUTE_POINT")
    private List<RoutePoint> routePoints;

    @OneToOne
    @JoinColumn(name = "LORRY")
    private Lorry lorry;

    @OneToMany
    @JoinTable(
            name = "ORDER_DRIVER",
            joinColumns = @JoinColumn(name="ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name="DRIVER_ID")
    )
    private Set<Driver> drivers;
}
