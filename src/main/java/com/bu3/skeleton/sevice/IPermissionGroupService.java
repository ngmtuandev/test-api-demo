package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.PermissionGroupDto;
import com.bu3.skeleton.dto.request.PermissionGroupRequest;

import java.util.List;

public interface IPermissionGroupService {

    void addPermissionGroup(PermissionGroupRequest request);

    List<PermissionGroupDto> findAllPermissionGroup();

}
