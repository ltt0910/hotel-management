package com.management.hotel.mapping;

import com.management.hotel.entity.UserEntity;
import com.management.hotel.model.dto.UserDto;
import com.management.hotel.model.request.CreateUserForm;
import com.management.hotel.model.request.UpdateUserForm;
import com.management.hotel.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {

    @Autowired
    private GroupMapping groupMapping;

    @Autowired
    private GroupRepository groupRepository;


    public UserDto convertToDto(UserEntity userEntity) {

        UserDto result = new UserDto();
        result.setId(userEntity.getId());
        result.setUsername(userEntity.getUsername());
        result.setFullName(userEntity.getFullName());
        result.setEmail(userEntity.getEmail());
        result.setImage(userEntity.getImage());
        result.setBirthday(userEntity.getBirthday());
        result.setSex(userEntity.isSex());
        result.setPhone(userEntity.getPhone());
        result.setPassword(userEntity.getPassword());
        result.setGroup(groupMapping.convertToDto(userEntity.getGroup()));

        return result;
    }

    public UserEntity convertToEntity(CreateUserForm userForm) {

        UserEntity result = new UserEntity();

        result.setId(userForm.getId());
        result.setUsername(userForm.getUsername());
        result.setFullName(userForm.getFullName());
        result.setPassword(userForm.getPassword());
        result.setEnable(userForm.getEnable());
        result.setGroup(groupRepository.findById(userForm.getGroupId()));

        return result;

    }

    public UserEntity convertToEntity(UpdateUserForm userForm) {

        UserEntity result = new UserEntity();

        result.setId(userForm.getId());
        result.setUsername(userForm.getUsername());
        result.setFullName(userForm.getFullName());
        result.setEnable(userForm.getEnable());
        result.setGroup(groupRepository.findById(userForm.getGroupId()));

        return result;

    }

}
