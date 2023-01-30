package com.management.hotel.service.impl;

import com.management.hotel.builder.Response;
import com.management.hotel.entity.UserEntity;
import com.management.hotel.exception.GroupException;
import com.management.hotel.exception.HttpStatusCode;
import com.management.hotel.exception.UserException;
import com.management.hotel.mapping.UserMapping;
import com.management.hotel.model.dto.UserDto;
import com.management.hotel.model.request.CreateUserForm;
import com.management.hotel.model.request.UpdateUserForm;
import com.management.hotel.repository.GroupRepository;
import com.management.hotel.repository.UserRepository;
import com.management.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepositoryImpl;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    private UserMapping userMapping;

    @Override
    public Response findAll() throws Exception {

        List<UserEntity> userEntities = userRepositoryImpl.findAll();
        List<UserDto> result = new ArrayList<>();
        if (userEntities.isEmpty()) {
            throw new UserException("list user is empty", HttpStatus.NOT_FOUND.value());
        } else {
            for (UserEntity user : userEntities) {
                result.add(userMapping.convertToDto(user));
            }
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("get data user successfully")
                .buildData(result)
                .build();
    }

    @Override
    public Response findById(Long id) throws Exception {

        UserEntity user = userRepositoryImpl.findById(id);
        if (user == null) {
            throw new UserException("user not found", HttpStatus.NOT_FOUND.value());
        }
        UserDto result = userMapping.convertToDto(user);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("get data user successfully")
                .buildData(result)
                .build();
    }

    @Override
    public Response create(CreateUserForm userForm) throws Exception {

        UserEntity user = userMapping.convertToEntity(userForm);

        UserDto result;
        if (userRepositoryImpl.checkExistByUserName(user) || !groupRepository.checkExist(userForm.getGroupId())) {
            if (userRepositoryImpl.checkExistByUserName(user)) {
                throw new UserException("user existed", HttpStatusCode.USER_EXIST);
            } else {
                throw new GroupException("group not exist", HttpStatus.NOT_FOUND.value());
            }

        } else {
            UserEntity userEntity = userRepositoryImpl.create(user);
            result = userMapping.convertToDto(userEntity);
        }

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("create user successfully")
                .buildData(result)
                .build();
    }

    @Override
    public Response update(UpdateUserForm userForm) throws Exception {

        UserDto result;
        UserEntity user = userMapping.convertToEntity(userForm);
        UserEntity userUpdate;

        if (!groupRepository.checkExist(userForm.getGroupId())) {
            throw new GroupException("group not exist", HttpStatus.NOT_FOUND.value());
        }
        if (userRepositoryImpl.findById(userForm.getId()) == null) {
            throw new UserException("user not found", HttpStatus.NOT_FOUND.value());

        } else {
            UserEntity userEntity = userRepositoryImpl.findById(userForm.getId());
            if (userForm.getUsername().equals(userEntity.getUsername())) {
                userUpdate = userRepositoryImpl.updateA(user);
            } else {
                if (userRepositoryImpl.checkExistByUserName(user)) {
                    throw new UserException("username is existing", HttpStatusCode.USER_EXIST);
                }
                userUpdate = userRepositoryImpl.updateB(user);
            }
        }
        result = userMapping.convertToDto(userUpdate);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("update user successfully")
                .buildData(result)
                .build();
    }

    @Override
    public Response delete(Long id) throws Exception {

        if (userRepositoryImpl.findById(id) == null) {
            throw new UserException("user not found", HttpStatus.NOT_FOUND.value());
        }
        userRepositoryImpl.delete(id);

        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("delete user successfully")
                .buildData("")
                .build();
    }

    @Override
    public Response findByGroupId(Long id) throws Exception {

        if (!groupRepository.checkExist(id)) {
            throw new GroupException("group not found", HttpStatus.NOT_FOUND.value());
        }
        List<UserDto> result = new ArrayList<>();
        List<UserEntity> entities = userRepositoryImpl.findByGroupId(id);

        for (UserEntity user : entities) {
            result.add(userMapping.convertToDto(user));
        }
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("find user by group id successfully")
                .buildData(result)
                .build();

    }

    @Override
    public String getUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return currentPrincipalName;
    }

    @Override
    public Response getUserByUsername(String username) throws UserException {
        UserEntity user = userRepositoryImpl.findByUsername(username);
        if (user == null) {
            throw new UserException("user not found", HttpStatus.NOT_FOUND.value());
        }
        UserDto result = userMapping.convertToDto(user);
        return new Response.Builder(1, HttpStatus.OK.value())
                .buildMessage("find user by username successfully")
                .buildData(result)
                .build();
    }
}
