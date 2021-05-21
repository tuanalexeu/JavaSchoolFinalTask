package com.alekseytyan.logiweb.dto;

import com.alekseytyan.logiweb.entity.enums.LoadStatus;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class LoadDTO implements Serializable {

    private Long id;

    private CityDTO cityLoad;

    private CityDTO cityUnload;

    private String name;

    private int weight;

    private LoadStatus status;

    @ToString.Exclude
    private OrderDTO order;
}
