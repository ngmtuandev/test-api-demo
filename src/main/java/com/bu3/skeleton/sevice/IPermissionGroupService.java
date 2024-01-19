package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.permissiongroup.PermissionGroupRequest;
import com.bu3.skeleton.dto.response.permissiongroup.PermissionGroupResponse;
import com.bu3.skeleton.dto.response.permissiongroup.PermissionGroupResponses;

public interface IPermissionGroupService {

    PermissionGroupResponse addPermissionGroup(PermissionGroupRequest request);
    PermissionGroupResponse updatePermissionGroup(PermissionGroupRequest request);

    PermissionGroupResponse deletePermissionGroup(String permissionGroupName);

    PermissionGroupResponses findAllPermissionGroup(int currentPage, int limitPage);

}
