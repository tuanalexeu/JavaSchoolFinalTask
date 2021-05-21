package com.alekseytyan.logiweb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatsDTO {
    private Long id;
    private boolean isFinished;
    private String regNum;
    private String Driver1Name;
    private String Driver2Name;
}