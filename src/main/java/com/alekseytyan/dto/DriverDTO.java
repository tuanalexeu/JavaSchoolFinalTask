package com.alekseytyan.dto;

import com.alekseytyan.entity.*;
import lombok.Data;

@Data
public class DriverDTO {

    private int id;

    private String firstName;

    private String lastName;

    private int hours_worked;

    private Driver.DriverState state;

    private City city;

    private Lorry lorry;

    private Order order;

    private User user;
}
