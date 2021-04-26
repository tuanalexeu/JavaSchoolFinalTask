package com.alekseytyan.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private boolean isFinished;

    @ToString.Exclude
    private List<LoadDTO> loads;

    @ToString.Exclude
    private LorryDTO lorry;

    @ToString.Exclude
    private List<DriverDTO> drivers;
}
