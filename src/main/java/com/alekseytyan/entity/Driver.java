package com.alekseytyan.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Comparator;
import java.util.Objects;

@Entity
@Table(name = "DRIVER")
public class Driver implements Comparable<Driver> {

    public enum DriverState {
        VACATION, IN_SHIFT, DRIVING
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getHours_worked() {
        return hours_worked;
    }

    public void setHours_worked(int hours_worked) {
        this.hours_worked = hours_worked;
    }

    public DriverState getState() {
        return state;
    }

    public void setState(DriverState state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Lorry getTruck() {
        return lorry;
    }

    public void setTruck(Lorry lorry) {
        this.lorry = lorry;
    }

    @Override
    public int compareTo(Driver o) {
        return Comparator
                .comparing(Driver::getID)
                .compare(this, o);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver)) return false;
        Driver driver = (Driver) o;
        return ID == driver.ID
                && hours_worked == driver.hours_worked
                && firstName.equals(driver.firstName)
                && lastName.equals(driver.lastName)
                && state == driver.state
                && city.equals(driver.city)
                && lorry.equals(driver.lorry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, firstName, lastName, hours_worked, state, city, lorry);
    }
}
