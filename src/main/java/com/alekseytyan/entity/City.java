package com.alekseytyan.entity;

import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode
public class City {
    @Id
    @Column(name = "NAME", length = 30, nullable = false)
    @Size(min = 2, max = 30)
    private String name;
}
