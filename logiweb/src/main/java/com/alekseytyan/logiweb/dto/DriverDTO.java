package com.alekseytyan.logiweb.dto;

import com.alekseytyan.logiweb.entity.enums.DriverState;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class DriverDTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private int hoursWorked;

    private DriverState state;

    private CityDTO city;

    private LorryDTO lorry;

    @ToString.Exclude
    private OrderDTO order;

    @ToString.Exclude
    private UserDTO user;
}
