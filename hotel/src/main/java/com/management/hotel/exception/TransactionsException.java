package com.management.hotel.exception;

public class TransactionsException extends Exception {
    private int code;

    public TransactionsException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
