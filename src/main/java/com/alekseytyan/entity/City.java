package com.alekseytyan.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "CITY")
@Getter @Setter @NoArgsConstructor
public class City {
    @Id
    @Column(name = "NAME", length = 30)
    @Size(min = 2, max = 30)
    private String name;
}
