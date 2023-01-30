package com.management.hotel.service.impl;

import com.management.hotel.entity.RoleEntity;
import com.management.hotel.exception.HttpStatusCode;
import com.management.hotel.exception.RoleException;
import com.management.hotel.mapping.RoleMapping;
import com.management.hotel.model.dto.RoleDto;
import com.management.hotel.repository.RoleRepository;
import com.management.hotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepositoryImpl;

    @Autowired
    private RoleMapping roleMapping;

    @Override
    public List<RoleDto> findByGroupId(Long id) {

        List<RoleEntity> roles = roleRepositoryImpl.findByGroupId(id);
        List<RoleDto> result = new ArrayList<>();
        if (roles != null) {
            for (RoleEntity item : roles) {
                result.add(roleMapping.map(item));
            }
        }

        return result;
    }
}
