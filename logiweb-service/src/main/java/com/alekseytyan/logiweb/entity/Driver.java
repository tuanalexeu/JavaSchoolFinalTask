package com.alekseytyan.logiweb.entity;

import com.alekseytyan.logiweb.entity.security.User;
import com.alekseytyan.logiweb.entity.enums.DriverState;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "DRIVER")
@NamedQueries({
        @NamedQuery(name = "Driver.findByUser",
                query = "SELECT d FROM Driver d where d.user.email = :email"),
        @NamedQuery(name = "Driver.findCoDrivers",
                query = "SELECT d FROM Driver d where d.order.id = :id"),
        @NamedQuery(name = "Driver.findSuitableDrivers",
                query = "SELECT d FROM Driver d where (d.hoursWorked + :hours <= 176) AND d.city.name = :cityName AND d.order IS NULL"),
        @NamedQuery(name = "Driver.findWithoutUser",
                query = "SELECT d FROM Driver d where d.user.email IS NULL"),
        @NamedQuery(name = "Driver.countAvailable",
                query = "SELECT COUNT(d) FROM Driver d where d.order IS NULL"),
        @NamedQuery(name = "Driver.countUnavailable",
                query = "SELECT COUNT(d) FROM Driver d where d.order IS NOT NULL")
})
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Driver {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "HOURS_WORKED", nullable = false)
    @Min(0) @Max(176)
    private int hoursWorked;

    @Column(name = "STATE", nullable = false)
    @Enumerated(EnumType.STRING)
    private DriverState state;

    @ManyToOne
    @JoinColumn(name = "CITY")
    private City city;

    @ManyToOne
    @JoinColumn(name = "LORRY")
    private Lorry lorry;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_EMAIL")
    private User user;
}
