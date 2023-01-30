package com.management.hotel.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BaseEntity {

    private Long id;

    private Boolean enable;

    private Timestamp createDate;

    private Timestamp modifyDate;

    private String createBy;

    private String modifyBy;


}
