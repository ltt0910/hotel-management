package com.management.hotel.model.request;

import lombok.Data;

@Data
public class TransactionForm {

    private Long code;

    private String customerCode;

    private String customerName;

    private String customerPhone;

}
