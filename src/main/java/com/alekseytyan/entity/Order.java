package com.alekseytyan.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ORDER_LOGIWEB")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class Order {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IS_FINISHED")
    private boolean isFinished;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROUTE_POINT")
    @Fetch(FetchMode.SUBSELECT)
    private List<RoutePoint> routePoints;

    @OneToOne
    @JoinColumn(name = "LORRY")
    private Lorry lorry;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "ORDER_DRIVER",
            joinColumns = @JoinColumn(name="ORDER_ID"),
            inverseJoinColumns = @JoinColumn(name="DRIVER_ID")
    )
    private List<Driver> drivers;
}
