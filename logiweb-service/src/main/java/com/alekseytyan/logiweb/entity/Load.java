package com.alekseytyan.logiweb.entity;

import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

/**
 * Class represents LOAD entity which needs delivering from A to B point
 */
@Entity
@Table(name = "LOAD_LOGIWEB")
@NamedQueries({
        @NamedQuery(name = "Load.findByToken",
                query = "select l from Load l where l.token = :token"),
        @NamedQuery(name = "Load.findAllByClientId",
                query = "select l from Load l where l.clientId = :clientId")
})
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
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
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    @Min(0)
    private int weight;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private LoadStatus status;

    @Column(name = "CLIENT_ID")
    private String clientId;

    @Column(name = "LOAD_TOKEN")
    private String token;

}
