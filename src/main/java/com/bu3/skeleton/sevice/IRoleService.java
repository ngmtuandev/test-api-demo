package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.role.RoleAddRequest;
import com.bu3.skeleton.dto.response.role.RoleResponse;
import com.bu3.skeleton.dto.response.role.RoleResponses;

import java.util.List;
import java.util.UUID;

public interface IRoleService {

    RoleResponse addRole(RoleAddRequest request);

    RoleResponse updateRole(RoleAddRequest request);
    RoleResponse deleteRole(RoleAddRequest request);

    RoleResponse findById(UUID roleId);

    RoleResponses findAllRole();
}
