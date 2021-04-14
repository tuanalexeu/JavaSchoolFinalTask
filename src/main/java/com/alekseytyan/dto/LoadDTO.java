package com.alekseytyan.dto;

import com.alekseytyan.entity.Load;
import com.alekseytyan.entity.Order;
import lombok.Data;

@Data
public class LoadDTO {
    private int id;

    private String name;

    private int weight;

    private Order order;

    private Load.LoadStatus status;
}
