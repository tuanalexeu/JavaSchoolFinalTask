package com.alekseytyan.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "LOAD_LOGIWEB")
public class Load {

    public enum LoadStatus {
        PREPARED, SENT, DELIVERED
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    @Min(0)
    private int weight;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private LoadStatus status;

}
