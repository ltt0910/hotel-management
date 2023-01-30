package com.management.hotel.exception;

public class RoleException extends Exception {

    private int code;

    public RoleException(String message, int code) {
        super(message);
        this.code = code;
    }
}
