package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.RoleRequest;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.exception.ResourceDuplicateException;
import com.bu3.skeleton.exception.ResourceNotFoundException;
import com.bu3.skeleton.mapper.RoleDtoMapper;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.sevice.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepo roleRepo;

    private final RoleDtoMapper roleDtoMapper;

    @Override
    public void addRole(RoleRequest request) {
        if (roleRepo.existsByRoleName(request.getRoleName())) {
            throw new ResourceDuplicateException(Translator.toLocale(TransitionCode.ROLE_NAME_EXISTS));
        }

        roleRepo.save(
                Role.builder()
                        .roleName(request.getRoleName())
                        .roleCode(request.getRoleCode())
                        .status(SystemConstant.ROLE_ACTIVE)
                        .build()
        );
    }

    @Override
    public void updateRole(RoleRequest request) {
        Role role = roleRepo.findRoleByRoleName(request.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException(Translator.toLocale(TransitionCode.FIND_ROLE_BY_ROLE_NAME_NOT_FOUND)));

        if (roleRepo.existsByRoleName(request.getRoleName())) {
            throw new ResourceDuplicateException(Translator.toLocale(TransitionCode.ROLE_NAME_EXISTS));
        }

        if (!role.getRoleName().equals(request.getRoleName())) {
            role.setRoleName(request.getRoleName());
        }

        if (!role.getRoleCode().equals(request.getRoleCode())) {
            role.setRoleCode(request.getRoleCode());
        }

        roleRepo.save(role);
    }

    @Override
    public List<RoleDto> findAllRole() {
        List<Role> all = roleRepo.findAll();

        return all.stream()
                .map(roleDtoMapper)
                .toList();
    }
}
