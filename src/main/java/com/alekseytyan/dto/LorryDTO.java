package com.alekseytyan.dto;

import com.alekseytyan.entity.City;
import com.alekseytyan.entity.Order;
import lombok.Data;


@Data
public class LorryDTO {

    private String regNum;

    private int shiftTime;

    private int capacity;

    private boolean isBroken;

    private City city;

    private Order order;
}
