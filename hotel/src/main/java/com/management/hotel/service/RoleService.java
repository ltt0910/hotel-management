package com.management.hotel.service;

import com.management.hotel.model.dto.RoleDto;

import java.util.List;

public interface RoleService {

    List<RoleDto> findByGroupId(Long id);

}
