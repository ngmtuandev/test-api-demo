package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.permission.PermissionRequest;
import com.bu3.skeleton.dto.request.permission.PermissionUpdateRequest;
import com.bu3.skeleton.dto.response.permission.PermissionResponses;

import java.util.UUID;

public interface IPermissionService {

    void addPermission(PermissionRequest request);

    void updatePermission(PermissionUpdateRequest request);

    void deletePermission(UUID permissionId);

    PermissionResponses findAllPermission(Integer currentPage, Integer litMitPage);
}
