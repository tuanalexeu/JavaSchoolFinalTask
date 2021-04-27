package com.alekseytyan.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Class represents Order entity which contain list of loads, responsible drivers and used lorry
 */

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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "order")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Load> loads;

    @OneToOne
    @JoinColumn(name = "LORRY")
    private Lorry lorry;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.MERGE)
    private List<Driver> drivers;

    @Column(name = "VERIFIED", columnDefinition = "boolean default false")
    private boolean verified;
}
