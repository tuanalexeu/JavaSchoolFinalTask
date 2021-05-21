package com.alekseytyan.logiweb.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class MapDTO implements Serializable {

    private Long id;

    private CityDTO cityFrom;

    private CityDTO cityTo;

    private int distance;
}
