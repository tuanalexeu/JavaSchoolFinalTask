package com.alekseytyan.entity;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Class represents Order entity which contain list of loads, responsible drivers and used lorry
 */

@Entity
@Table(name = "ORDER_LOGIWEB")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "Order.findVerified",
                    query = "select o from Order o where o.isVerified = TRUE")
})
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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "LORRY", referencedColumnName = "ID")
    private Lorry lorry;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.MERGE)
    @Size(max = 2)
    private List<Driver> drivers;

    @Column(name = "VERIFIED", columnDefinition = "boolean default false")
    private boolean isVerified;
}
