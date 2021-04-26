package com.alekseytyan.dto;

import lombok.Data;

@Data
public class MapDTO {

    private Long id;

    private CityDTO cityFrom;

    private CityDTO cityTo;

    private int distance;
}
