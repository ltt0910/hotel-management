package com.management.hotel.model.request;

import lombok.Data;

@Data
public class CreateUserForm {

    private Long id;

    private String username;

    private String password;

    private Boolean enable;

    private String fullName;

    private Long groupId;

}
