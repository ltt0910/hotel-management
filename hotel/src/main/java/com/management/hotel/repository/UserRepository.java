package com.management.hotel.repository;

import com.management.hotel.entity.UserEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {

    List<UserEntity> findAll();

    UserEntity findById(Long id);

    List<UserEntity> findByGroupId(Long id);

    UserEntity findByUsername(String username);

    UserEntity create(UserEntity user) throws SQLException;

    Boolean checkExistByUserName(UserEntity user);

    Boolean checkExistById(UserEntity user);

    UserEntity updateA(UserEntity user) throws SQLException;

    UserEntity updateB(UserEntity user) throws SQLException;

    void delete(long id) throws SQLException;

}
