package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.permissionrole.PermissionRoleRequest;
import com.bu3.skeleton.dto.response.permissionrole.PermissionRoleResponse;
import com.bu3.skeleton.dto.response.permissionrole.PermissionRoleResponses;

public interface IPermissionRoleService {

    PermissionRoleResponse addPermissionRole(PermissionRoleRequest request);

    PermissionRoleResponse updatePermissionRole(PermissionRoleRequest request);

    PermissionRoleResponse deletePermissionRole(PermissionRoleRequest request);

    PermissionRoleResponses findAllPermissionRole(PermissionRoleRequest request);
}
