package com.management.hotel.repository;

import com.management.hotel.entity.RoleEntity;

import java.util.List;

public interface RoleRepository {

    RoleEntity findById(Long id);

    List<RoleEntity> findByGroupId(Long id) ;
}
