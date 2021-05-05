package com.alekseytyan.logiweb.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "LORRY")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "order")
@NamedQueries({
        @NamedQuery(name = "Lorry.findSuitableLorries",
                    query = "SELECT l from Lorry l " +
                            "where l.capacity >= :weight " +
                            "AND l.isBroken = FALSE " +
                            "AND NOT EXISTS (SELECT o from Order o where o.lorry.regNum = l.regNum)")
})
public class Lorry {
    @Id
    @Column(name = "ID", length = 7)
    @Pattern(regexp="[a-zA-Z]{2}\\d{5}")
    @Size(min = 7, max = 7)
    private String regNum;

    @Column(name = "SHIFT_TIME")
    private int shiftTime;

    @Column(name = "CAPACITY")
    @Min(0)
    private int capacity;

    @Column(name = "IS_BROKEN", columnDefinition = "boolean default false")
    private boolean isBroken;

    @ManyToOne
    @JoinColumn(name = "CITY")
    private City city;

    @OneToOne(mappedBy = "lorry")
    private Order order;
}
