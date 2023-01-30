package com.management.hotel.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserEntity extends BaseEntity {

    private String username;

    private String password;

    private String fullName;

    private String image;

    private boolean sex;

    private String phone;

    private Timestamp birthday;

    private String email;

    private GroupEntity group;

}
