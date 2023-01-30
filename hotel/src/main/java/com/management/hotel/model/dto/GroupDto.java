package com.management.hotel.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class GroupDto {

    private Long id;

    private String code;

    private String name;

    private List<RoleDto> roles;

}
