package com.alekseytyan.logiweb.dto;

import com.alekseytyan.logiweb.entity.enums.LoadStatus;
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

    public static ClientLoadDTO convert(LoadDTO loadDTO) {
        return new ClientLoadDTO(
                loadDTO.getClientId(),
                loadDTO.getCityLoad().getName(),
                loadDTO.getCityUnload().getName(),
                loadDTO.getName(),
                loadDTO.getWeight(),
                loadDTO.getStatus(),
                loadDTO.getToken()
        );
    }
}