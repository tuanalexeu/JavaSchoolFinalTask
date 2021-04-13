package com.alekseytyan.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CITY")
public class City {

    @Id
    @Column(name = "NAME", length = 30)
    @Size(min = 2, max = 30)
    private String name;

}
