package com.management.hotel.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {

    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String fullName;

    private String email;

    private String phone;

    private Timestamp birthday;

    private boolean sex;

    private String image;

    private GroupDto group;

}
