package com.alekseytyan.dto;

import com.alekseytyan.entity.enums.LoadStatus;
import lombok.Data;
import lombok.ToString;

@Data
public class LoadDTO {

    private Long id;

    private CityDTO cityLoad;

    private CityDTO cityUnload;

    private String name;

    private int weight;

    private LoadStatus status;

    @ToString.Exclude
    private OrderDTO order;
}
