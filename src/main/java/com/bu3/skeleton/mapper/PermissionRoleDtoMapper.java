package com.bu3.skeleton.mapper;

import com.bu3.skeleton.dto.PermissionRoleDto;
import com.bu3.skeleton.entity.PermissionRole;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PermissionRoleDtoMapper implements Function<PermissionRole, PermissionRoleDto> {

    @Override
    public PermissionRoleDto apply(PermissionRole permissionRole) {
        return PermissionRoleDto.builder()
                .permissionRoleId(permissionRole.getId())
                .permissionId(permissionRole.getPermission().getId())
                .permissionCode(permissionRole.getPermission().getPermissionCode())
                .roleId(permissionRole.getRole().getId())
                .roleName(permissionRole.getRole().getRoleName())
                .isDeleted(permissionRole.getIsDeleted())
                .build();
    }
}
