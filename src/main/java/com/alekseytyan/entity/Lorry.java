package com.alekseytyan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "LORRY")
@Getter @Setter @NoArgsConstructor
public class Lorry {

    @Id
    @Column(name = "ID", length = 7)
    @Pattern(regexp="[a-zA-Z]{2}\\d{5}")
    @Size(min = 7, max = 7)
    private String reg_num;

    @Column(name = "SHIFT_TIME")
    private int shift_time;

    @Column(name = "CAPACITY")
    private int capacity;

    @Column(name = "IS_BROKEN")
    private boolean isBroken;

    @ManyToOne
    @JoinColumn(name = "CITY")
    private City city;

    @OneToOne(mappedBy = "lorry")
    private Order order;
}
