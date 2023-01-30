package com.management.hotel.configuration;

import com.management.hotel.entity.UserEntity;
import com.management.hotel.mapping.UserMapping;
import com.management.hotel.model.dto.RoleDto;
import com.management.hotel.model.dto.UserDto;
import com.management.hotel.repository.UserRepository;
import com.management.hotel.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepositoryImpl;

    @Autowired
    private RoleService roleServiceImpl;
    @Autowired
    private UserMapping userMapping;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepositoryImpl.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        List<RoleDto> roles = roleServiceImpl.findByGroupId(user.getGroup().getId());
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (roles != null) {
            roles.stream().forEach(role -> {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                grantedAuthorities.add(authority);
            });
        }
        UserDto userDto = userMapping.convertToDto(user);
        UserDetails userDetails = new User(username, userDto.getPassword(), grantedAuthorities);

        return userDetails;
    }
}
