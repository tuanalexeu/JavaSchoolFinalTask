package com.alekseytyan.logiweb.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "MAP")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class DistanceMap {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FROM_CITY")
    private City cityFrom;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TO_CITY")
    private City cityTo;

    @Column(name = "DISTANCE")
    @Min(0)
    private int distance;

}
