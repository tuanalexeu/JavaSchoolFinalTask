package com.alekseytyan.logiweb.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
public class LorryDTO implements Serializable {

    private String regNum;

    private int shiftTime;

    private int capacity;

    private boolean isBroken;

    private CityDTO city;

    @ToString.Exclude
    private OrderDTO order;
}
