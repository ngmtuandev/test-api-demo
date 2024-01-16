package com.bu3.skeleton.mapper;

import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.entity.Role;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RoleDtoMapper implements Function<Role, RoleDto> {

    @Override
    public RoleDto apply(Role role) {
        return RoleDto.builder()
                .roleName(role.getRoleName())
                .roleCode(role.getRoleCode())
                .build();
    }
}
