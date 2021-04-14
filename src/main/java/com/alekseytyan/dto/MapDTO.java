package com.alekseytyan.dto;

import com.alekseytyan.entity.City;
import lombok.Data;

@Data
public class MapDTO {

    private int id;

    private City cityFrom;

    private City cityTo;

    private int distance;
}
