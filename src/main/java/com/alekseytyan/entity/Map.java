package com.alekseytyan.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

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

}
