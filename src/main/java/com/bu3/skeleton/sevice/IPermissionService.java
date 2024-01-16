package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.PermissionDto;
import com.bu3.skeleton.dto.request.PermissionRequest;

import java.util.List;

public interface IPermissionService {

    void addPermission(PermissionRequest request);

    List<PermissionDto> findAllPermission();
}
