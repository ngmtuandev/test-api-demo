package com.bu3.skeleton.mapper;

import com.bu3.skeleton.dto.PermissionDto;
import com.bu3.skeleton.entity.Permission;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PermissionDtoMapper implements Function<Permission, PermissionDto> {

    @Override
    public PermissionDto apply(Permission permission) {
        return PermissionDto.builder()
                .permissionId(permission.getId())
                .permissionCode(permission.getPermissionCode())
                .isDeleted(permission.getIsDeleted())
                .description(permission.getDescription())
                .build();
    }
}
