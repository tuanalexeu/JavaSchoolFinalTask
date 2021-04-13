package com.alekseytyan.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "LORRY")
public class Lorry implements Comparable<Lorry> {

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


    public void setReg_num(String id) {
        this.reg_num = id;
    }

    public String getReg_num() {
        return reg_num;
    }

    public int getShift_time() {
        return shift_time;
    }

    public void setShift_time(int shift_time) {
        this.shift_time = shift_time;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lorry)) return false;
        Lorry lorry = (Lorry) o;
        return shift_time == lorry.shift_time
                && capacity == lorry.capacity
                && isBroken == lorry.isBroken
                && reg_num.equals(lorry.reg_num)
                && city.equals(lorry.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reg_num, shift_time, capacity, isBroken, city);
    }

    @Override
    public int compareTo(Lorry o) {
        return Comparator
                .comparing(Lorry::getReg_num)
                .compare(this, o);
    }
}
