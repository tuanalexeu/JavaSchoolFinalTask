package com.alekseytyan.entity;

import com.alekseytyan.entity.enums.RouteType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ROUTE_POINT")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class RoutePoint {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
