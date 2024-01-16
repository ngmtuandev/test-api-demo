package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.request.PermissionRoleRequest;
import com.bu3.skeleton.entity.Permission;
import com.bu3.skeleton.entity.PermissionRole;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.exception.ResourceNotFoundException;
import com.bu3.skeleton.repository.IPermissionRepo;
import com.bu3.skeleton.repository.IPermissionRoleRepo;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.sevice.IPermissionRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionRoleServiceImpl implements IPermissionRoleService {

    private final IPermissionRoleRepo permissionRoleRepo;

    private final IPermissionRepo permissionRepo;

    private final IRoleRepo roleRepo;

    @Override
    public void addPermissionRole(PermissionRoleRequest request) {
        Role role = roleRepo.findRoleByRoleName(request.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException(Translator.toLocale(TransitionCode.FIND_ROLE_BY_ROLE_NAME_NOT_FOUND)));

        Permission permission = permissionRepo.findPermissionByPermissionCode(request.getPermissionCode())
                .orElseThrow(() -> new ResourceNotFoundException(Translator.toLocale(TransitionCode.PERMISSION_FIND_NOT_FOUND)));

        permissionRoleRepo.save(
                PermissionRole.builder()
                        .permission(permission)
                        .role(role)
                        .build()
        );
    }
}
