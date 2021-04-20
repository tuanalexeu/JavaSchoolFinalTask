package com.alekseytyan.dto;

import com.alekseytyan.entity.enums.DriverState;
import lombok.Data;

@Data
public class DriverDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private int hoursWorked;

    private DriverState state;

    private CityDTO city;

    private LorryDTO lorry;

    private OrderDTO order;

    private UserDTO user;
}
