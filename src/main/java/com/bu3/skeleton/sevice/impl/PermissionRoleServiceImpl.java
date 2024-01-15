package com.bu3.skeleton.sevice.impl;

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
                .orElseThrow(() -> new ResourceNotFoundException("role not found!."));

        Permission permission = permissionRepo.findPermissionByPermissionCode(request.getPermissionCode())
                .orElseThrow(() -> new ResourceNotFoundException("permission not found!."));

        permissionRoleRepo.save(
                PermissionRole.builder()
                        .permission(permission)
                        .role(role)
                        .build()
        );
    }
}
