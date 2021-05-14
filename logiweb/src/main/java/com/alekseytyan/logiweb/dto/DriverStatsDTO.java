package com.alekseytyan.logiweb.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DriverStatsDTO implements Serializable {
    private long available;
    private long unavailable;
}
