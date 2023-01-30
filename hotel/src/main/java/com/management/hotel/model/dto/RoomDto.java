package com.management.hotel.model.dto;

import lombok.Data;

@Data
public class RoomDto {

    private Long id;

    private Long code;

    private String description;

    private String status;

    private String type;

    private Integer price;

    private String image;


}
