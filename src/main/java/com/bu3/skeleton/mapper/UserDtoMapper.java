package com.bu3.skeleton.mapper;

import com.bu3.skeleton.dto.PermissionDto;
import com.bu3.skeleton.dto.PermissionGroupDto;
import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.entity.Permission;
import com.bu3.skeleton.entity.PermissionGroup;
import com.bu3.skeleton.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class UserDtoMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        List<RoleDto> roles = new ArrayList<>();
        List<PermissionDto> permissions = new ArrayList<>();
        List<PermissionGroupDto> permissionGroups = new ArrayList<>();

        user.getUserRoles().forEach(userRole -> {
            roles.add(RoleDto.builder()
                    .roleId(userRole.getRole().getId())
                    .roleName(userRole.getRole().getRoleName())
                    .roleCode(userRole.getRole().getRoleCode())
                    .isDeleted(userRole.getRole().getIsDeleted())
                    .build());

            userRole.getRole().getPermissionRoles().forEach(permissionRole -> {
                Permission permission = permissionRole.getPermission();
                permissions.add(PermissionDto.builder()
                        .permissionId(permission.getId())
                        .permissionCode(permission.getPermissionCode())
                        .isDeleted(permission.getIsDeleted())
                        .description(permission.getDescription())
                        .build());

                PermissionGroup permissionGroup = permission.getPermissionGroup();
                permissionGroups.add(
                        PermissionGroupDto.builder()
                                .permissionGroupId(permissionGroup.getId())
                                .permissionGroupName(permissionGroup.getPermissionGroupName())
                                .isDeleted(permissionGroup.getIsDeleted())
                                .description(permissionGroup.getDescription())
                                .build()
                );
            });

        });


        return UserDto.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .gender(user.getGender())
                .dateOfBirth(user.getDateOfBirth())
                .fullName(user.getFullName())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .isDeleted(user.getIsDeleted())
                .roles(roles)
                .permissions(permissions)
                .permissionGroups(permissionGroups)
                .build();
    }
}
