package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.constant.ResourceBundleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.permissionrole.PermissionRoleRequest;
import com.bu3.skeleton.dto.response.permissionrole.PermissionRoleResponse;
import com.bu3.skeleton.dto.response.permissionrole.PermissionRoleResponses;
import com.bu3.skeleton.entity.Permission;
import com.bu3.skeleton.entity.PermissionRole;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.PermissionRoleDtoMapper;
import com.bu3.skeleton.repository.IPermissionRepo;
import com.bu3.skeleton.repository.IPermissionRoleRepo;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.sevice.IPermissionRoleService;
import com.bu3.skeleton.util.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionRoleServiceImpl implements IPermissionRoleService {

    private final IPermissionRoleRepo permissionRoleRepo;

    private final IPermissionRepo permissionRepo;

    private final IRoleRepo roleRepo;

    private final PermissionRoleDtoMapper permissionRoleDtoMapper;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    private Permission getPermission(UUID permissionId) {
        return permissionRepo.findById(permissionId)
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.PMS_002, getMessageBundle(ResourceBundleConstant.PMS_002)));
    }

    private Role getRole(UUID roleId) {
        return roleRepo.findById(roleId)
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.RL_002, getMessageBundle(ResourceBundleConstant.RL_002)));
    }

    @Override
    public PermissionRoleResponse addPermissionRole(PermissionRoleRequest request) {
        Role role = getRole(request.getRoleId());

        Permission permission = getPermission(request.getPermissionId());

        PermissionRole permissionRole = permissionRoleRepo.save(
                PermissionRole.builder()
                        .permission(permission)
                        .role(role)
                        .build()
        );

        return PermissionRoleResponse.builder()
                .code(ResourceBundleConstant.PMSR_005)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionRoleDtoMapper.apply(permissionRole))
                .message(getMessageBundle(ResourceBundleConstant.PMSR_005))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public PermissionRoleResponse updatePermissionRole(PermissionRoleRequest request) {
        PermissionRole permissionRole = permissionRoleRepo.findById(request.getPermissionId())
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.PMSR_002, getMessageBundle(ResourceBundleConstant.PMSR_002)));
        Role role = getRole(request.getRoleId());
        Permission permission = getPermission(request.getPermissionId());

        permissionRole.setPermission(permission);
        permissionRole.setRole(role);

        PermissionRole permissionRoleSave = permissionRoleRepo.save(permissionRole);
        return PermissionRoleResponse.builder()
                .code(ResourceBundleConstant.PMSR_003)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionRoleDtoMapper.apply(permissionRoleSave))
                .message(getMessageBundle(ResourceBundleConstant.PMSR_003))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public PermissionRoleResponse deletePermissionRole(PermissionRoleRequest request) {
        return null;
    }

    @Override
    public PermissionRoleResponses findAllPermissionRole(PermissionRoleRequest request) {
        return null;
    }
}
