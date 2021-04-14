package com.alekseytyan.dto;

import lombok.Data;


@Data
public class LorryDTO {

    private String regNum;

    private int shiftTime;

    private int capacity;

    private boolean isBroken;

    private CityDTO city;

    private OrderDTO order;
}
