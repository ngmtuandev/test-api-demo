package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.role.RoleAddRequest;
import com.bu3.skeleton.dto.response.role.RoleResponse;

import java.util.List;
import java.util.UUID;

public interface IRoleService {

    void addRole(RoleAddRequest request);

    void updateRole(RoleAddRequest request);

    RoleResponse findById(UUID roleId);

    List<RoleDto> findAllRole();
}
