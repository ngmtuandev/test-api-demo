package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.RoleRequest;
import com.bu3.skeleton.dto.response.RoleResponse;

import java.util.List;
import java.util.UUID;

public interface IRoleService {

    void addRole(RoleRequest request);

    void updateRole(RoleRequest request);

    RoleResponse findById(UUID roleId);

    List<RoleDto> findAllRole();
}
