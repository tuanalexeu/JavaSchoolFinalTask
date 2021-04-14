package com.alekseytyan.entity;

import com.alekseytyan.entity.enums.LoadStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "LOAD_LOGIWEB")
@Getter @Setter @NoArgsConstructor
public class Load {

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
