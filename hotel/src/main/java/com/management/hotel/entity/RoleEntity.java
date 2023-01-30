package com.management.hotel.entity;

import lombok.Data;

import java.util.List;

@Data
public class RoleEntity extends BaseEntity {

    private String code;

    private String name;

    private List<GroupEntity> groups;

}
