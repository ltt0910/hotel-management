package com.management.hotel.service;

import com.management.hotel.builder.Response;
import com.management.hotel.exception.UserException;
import com.management.hotel.model.request.CreateUserForm;
import com.management.hotel.model.request.UpdateUserForm;

public interface UserService {

    Response findAll() throws Exception;

    Response findById(Long id) throws Exception;

    Response create(CreateUserForm user) throws Exception;

    Response update(UpdateUserForm user) throws Exception;

    Response delete(Long id) throws Exception;

    Response findByGroupId(Long id) throws Exception;

    String getUsername();

    Response getUserByUsername(String username) throws UserException;
}
