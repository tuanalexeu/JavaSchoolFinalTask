package com.alekseytyan.client.dto;

import com.alekseytyan.client.dto.enums.LoadStatus;
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

    private LoadStatus status;

    private String token;

}