package com.alekseytyan.entity;

import com.alekseytyan.entity.enums.LoadStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Class represents LOAD entity which needs delivering from A to B point
 */
@Entity
@Table(name = "LOAD_LOGIWEB")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
public class Load {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CITY_LOAD", nullable = false)
    private City cityLoad;

    @ManyToOne
    @JoinColumn(name = "CITY_UNLOAD", nullable = false)
    private City cityUnload;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    @Min(0)
    private int weight;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private LoadStatus status;

}
