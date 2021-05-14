package com.alekseytyan.logiweb.entity;

import com.alekseytyan.logiweb.util.pathfinding.astar.GraphNode;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CITY")
@Getter @Setter @NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@NamedQueries({
        @NamedQuery(name = "City.findAllNames",
                    query = "select name from City")
})
public class City implements GraphNode {
    @Id
    @Column(name = "NAME", length = 30, nullable = false)
    @Size(min = 2, max = 30)
    private String name;

//    @Column(name = "LATITUDE")
//    private double latitude;
//
//    @Column(name = "LONGITUDE")
//    private double longitude;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getName() {
        return null;
    }
}
