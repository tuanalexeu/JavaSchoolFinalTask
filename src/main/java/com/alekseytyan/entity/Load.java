package com.alekseytyan.entity;

import com.alekseytyan.entity.state.LoadStatus;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "LOAD_LOGIWEB")
public class Load implements Comparable<Load> {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "WEIGHT")
    @Min(0)
    private int weight;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private LoadStatus status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LoadStatus getStatus() {
        return status;
    }

    public void setStatus(LoadStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Load)) return false;
        Load load = (Load) o;
        return id == load.id
                && weight == load.weight
                && name.equals(load.name)
                && status == load.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, weight, status);
    }

    @Override
    public int compareTo(Load o) {
        return Comparator.comparing(Load::getName).compare(this, o);
    }
}
