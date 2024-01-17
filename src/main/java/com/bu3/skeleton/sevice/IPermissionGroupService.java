package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.PermissionGroupRequest;
import com.bu3.skeleton.dto.response.PermissionGroupResponses;

public interface IPermissionGroupService {

    void addPermissionGroup(PermissionGroupRequest request);
    void updatePermissionGroup(PermissionGroupRequest request);

    void deletePermissionGroup(String permissionGroupName);

    PermissionGroupResponses findAllPermissionGroup(int currentPage, int limitPage);

}
