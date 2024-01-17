package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.PermissionDto;
import com.bu3.skeleton.dto.request.PermissionRequest;
import com.bu3.skeleton.dto.request.PermissionUpdateRequest;
import com.bu3.skeleton.dto.response.PermissionResponses;

import java.util.List;
import java.util.UUID;

public interface IPermissionService {

    void addPermission(PermissionRequest request);

    void updatePermission(PermissionUpdateRequest request);

    void deletePermission(UUID permissionId);

    PermissionResponses findAllPermission(Integer currentPage, Integer litMitPage);
}
