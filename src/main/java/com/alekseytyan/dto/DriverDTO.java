package com.alekseytyan.dto;

import com.alekseytyan.entity.*;
import lombok.Data;

@Data
public class DriverDTO {

    private int id;

    private String firstName;

    private String lastName;

    private int hoursWorked;

    private Driver.DriverState state;

    private CityDTO city;

    private LorryDTO lorry;

    private OrderDTO order;

    private UserDTO user;
}
