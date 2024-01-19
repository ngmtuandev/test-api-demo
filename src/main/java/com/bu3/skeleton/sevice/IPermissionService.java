package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.permission.PermissionRequest;
import com.bu3.skeleton.dto.request.permission.PermissionUpdateRequest;
import com.bu3.skeleton.dto.response.permission.PermissionResponse;
import com.bu3.skeleton.dto.response.permission.PermissionResponses;

import java.util.UUID;

public interface IPermissionService {

    PermissionResponse addPermission(PermissionRequest request);

    PermissionResponse updatePermission(PermissionUpdateRequest request);

    PermissionResponse deletePermission(UUID permissionId);

    PermissionResponses findAllPermission(Integer currentPage, Integer litMitPage);
}
