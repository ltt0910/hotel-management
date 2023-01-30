package com.management.hotel.entity;

import lombok.Data;

import java.util.List;

@Data
public class RoomEntity extends BaseEntity {

    private Long code;

    private int type;

    private Integer pricePerDay;

    private Boolean status;

    private String image;

    private String description;

    private List<TransactionEntity> transaction;

}
