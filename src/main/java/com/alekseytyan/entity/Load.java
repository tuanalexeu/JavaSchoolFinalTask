package com.alekseytyan.entity;

import com.alekseytyan.entity.enums.LoadStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "LOAD_LOGIWEB")
@Getter @Setter @NoArgsConstructor
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "Load.findByOrderId",
                query = "select l from Load l where l.order.id = :id")
})
public class Load {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
