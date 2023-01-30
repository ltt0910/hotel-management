package com.management.hotel.model.request;

import lombok.Data;

@Data
public class UpdateUserForm {

    private Long id;

    private String username;

    private Boolean enable;

    private String fullName;

    private Long groupId;

}
