package com.alekseytyan.dto;

import lombok.Data;
import lombok.ToString;


@Data
public class LorryDTO {

    private String regNum;

    private int shiftTime;

    private int capacity;

    private boolean isBroken;

    private CityDTO city;

    @ToString.Exclude
    private OrderDTO order;
}
