package com.management.hotel.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class TransactionEntity {

    private Long code;

    private Timestamp checkIn;

    private Timestamp checkOut;

    private String customerPhone;

    private String customerIdentification;

    private String customerName;

    private Long roomId;

    private Long price;

    private Boolean status;


}
