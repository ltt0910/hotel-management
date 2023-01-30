package com.management.hotel.mapping;

import com.management.hotel.entity.GroupEntity;
import com.management.hotel.entity.RoleEntity;
import com.management.hotel.model.dto.GroupDto;
import com.management.hotel.model.dto.RoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GroupMapping {

    @Autowired
    private RoleMapping roleMapping;

    public GroupDto convertToDto(GroupEntity entity) {

        GroupDto result = new GroupDto();
        result.setId(entity.getId());
        result.setCode(entity.getCode());
        result.setName(entity.getName());
        List<RoleDto> roles = new ArrayList<>();

        for (RoleEntity role : entity.getRole()) {
            roles.add(roleMapping.map(role));
        }
        result.setRoles(roles);

        return result;
    }
}
