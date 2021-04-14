package com.alekseytyan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DRIVER")
@Getter @Setter @NoArgsConstructor
public class Driver {

    public enum DriverState {
        VACATION, DUTY, DRIVING
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "FIRST_NAME")
    @Size(min = 1, max = 48)
    private String firstName;

    @Column(name = "LAST_NAME")
    @Size(min = 1, max = 48)
    private String lastName;

    @Column(name = "HOURS_WORKED")
    @Min(0)
    private int hours_worked;

    @Column(name = "STATE")
    @Enumerated(EnumType.STRING)
    private DriverState state;

    @ManyToOne
    @JoinColumn(name = "CITY")
    private City city;

    @ManyToOne(optional = false)
    @JoinColumn(name = "LORRY")
    private Lorry lorry;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @OneToOne
    @JoinColumn(name = "USER_EMAIL")
    private User user;
}
