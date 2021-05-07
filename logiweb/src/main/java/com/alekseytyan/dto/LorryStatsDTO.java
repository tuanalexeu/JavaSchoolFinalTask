package com.alekseytyan.dto;

import lombok.Data;

@Data
public class LorryStatsDTO {
    private long available;
    private long unavailable;
    private long broken;
}
