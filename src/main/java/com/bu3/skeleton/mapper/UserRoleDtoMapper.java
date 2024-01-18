package com.bu3.skeleton.mapper;

import com.bu3.skeleton.dto.UserRoleDto;
import com.bu3.skeleton.entity.UserRole;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserRoleDtoMapper implements Function<UserRole, UserRoleDto> {

    @Override
    public UserRoleDto apply(UserRole userRole) {
        return UserRoleDto.builder()
                .userRoleId(userRole.getId())
                .roleName(userRole.getRole().getRoleName())
                .email(userRole.getUser().getEmail())
                .fullName(userRole.getUser().getFullName())
                .isDeleted(userRole.getIsDeleted())
                .build();
    }
}
