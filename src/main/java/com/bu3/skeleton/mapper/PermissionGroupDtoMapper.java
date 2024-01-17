package com.bu3.skeleton.mapper;

import com.bu3.skeleton.dto.PermissionGroupDto;
import com.bu3.skeleton.entity.PermissionGroup;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class PermissionGroupDtoMapper implements Function<PermissionGroup, PermissionGroupDto> {

    @Override
    public PermissionGroupDto apply(PermissionGroup permissionGroup) {
        return PermissionGroupDto.builder()
                .permissionGroupId(permissionGroup.getId())
                .permissionGroupName(permissionGroup.getPermissionGroupName())
                .description(permissionGroup.getDescription())
                .build();
    }
}
