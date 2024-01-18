package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
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

    private final String permissionRoleCode = Translator.toLocale(TransitionCode.PERMISSION_ROLE_CODE);


    private Permission getPermission(UUID permissionId) {
        return permissionRepo.findById(permissionId)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.PERMISSION_CODE),
                        Translator.toLocale(TransitionCode.PERMISSION_FIND_NOT_FOUND)));
    }

    private Role getRole(UUID roleId) {
        return roleRepo.findById(roleId)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.ROLE_CODE),
                        Translator.toLocale(TransitionCode.FIND_ROLE_BY_ROLE_NAME_NOT_FOUND)));
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
                .code(permissionRoleCode)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionRoleDtoMapper.apply(permissionRole))
                .message(Translator.toLocale(TransitionCode.ADD_SUCCESS))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public PermissionRoleResponse updatePermissionRole(PermissionRoleRequest request) {
        PermissionRole permissionRole = permissionRoleRepo.findById(request.getPermissionId())
                .orElseThrow(() -> new ApiRequestException(permissionRoleCode, Translator.toLocale(TransitionCode.GET_SUCCESS)));
        Role role = getRole(request.getRoleId());
        Permission permission = getPermission(request.getPermissionId());

        permissionRole.setPermission(permission);
        permissionRole.setRole(role);

        PermissionRole permissionRoleSave = permissionRoleRepo.save(permissionRole);
        return PermissionRoleResponse.builder()
                .code(permissionRoleCode)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionRoleDtoMapper.apply(permissionRoleSave))
                .message(Translator.toLocale(TransitionCode.UPDATE_SUCCESS))
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
