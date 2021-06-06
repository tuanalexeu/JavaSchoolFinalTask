package com.alekseytyan.dashboard.dto;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class StatusDTO implements Serializable {

    private boolean isSuccess;

    public StatusDTO() {

    }

    public StatusDTO(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }
}
