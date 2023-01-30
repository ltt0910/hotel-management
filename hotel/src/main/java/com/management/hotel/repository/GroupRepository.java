package com.management.hotel.repository;

import com.management.hotel.entity.GroupEntity;

import java.util.List;

public interface GroupRepository {

    GroupEntity findById(Long id);

    Boolean checkExist(Long id);

}
