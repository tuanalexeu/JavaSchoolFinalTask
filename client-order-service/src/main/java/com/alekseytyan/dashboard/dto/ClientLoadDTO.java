package com.alekseytyan.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientLoadDTO implements Serializable {

    private String clientId;

    private String cityLoad;
    private String cityUnload;
    private String name;
    private int weight;

    private String orderToken;

}