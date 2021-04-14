package com.alekseytyan.dto;

import lombok.Data;

@Data
public class MapDTO {

    private int id;

    private CityDTO cityFrom;

    private CityDTO cityTo;

    private int distance;
}
