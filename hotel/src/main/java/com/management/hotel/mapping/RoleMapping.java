package com.management.hotel.mapping;

import com.management.hotel.model.dto.RoleDto;
import com.management.hotel.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleMapping {

    public RoleDto map(RoleEntity entity) {
        RoleDto result = new RoleDto();

        result.setId(entity.getId());
        result.setCode(entity.getCode());
        result.setName(entity.getName());
        return result;
    }

}
