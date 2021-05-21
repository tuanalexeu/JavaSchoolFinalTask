package com.alekseytyan.logiweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
public class OrderDTO implements Serializable {

    private Long id;

    private boolean isFinished;

    private boolean isVerified;

    @ToString.Exclude
    @JsonIgnore
    private List<LoadDTO> loads;

    @ToString.Exclude
    @JsonIgnore
    private LorryDTO lorry;

    @ToString.Exclude
    @JsonIgnore
    private List<DriverDTO> drivers;
}
