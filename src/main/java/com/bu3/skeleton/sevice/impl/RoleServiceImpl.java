package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.constant.ResourceBundleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.role.RoleAddRequest;
import com.bu3.skeleton.dto.response.role.RoleResponse;
import com.bu3.skeleton.dto.response.role.RoleResponses;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.RoleDtoMapper;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.sevice.IRoleService;
import com.bu3.skeleton.util.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements IRoleService {

    private final IRoleRepo roleRepo;

    private final RoleDtoMapper roleDtoMapper;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public RoleResponse addRole(RoleAddRequest request) {
        if (roleRepo.existsByRoleName(request.getRoleName())) {
            throw new ApiRequestException(ResourceBundleConstant.RL_002, getMessageBundle(ResourceBundleConstant.RL_002));
        }

        Role role = roleRepo.save(
                Role.builder()
                        .roleName(request.getRoleName())
                        .roleCode(request.getRoleCode())
                        .isDeleted(SystemConstant.ACTIVE)
                        .build()
        );

        return RoleResponse.builder()
                .code(ResourceBundleConstant.RL_007)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(roleDtoMapper.apply(role))
                .message(getMessageBundle(ResourceBundleConstant.RL_007))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public RoleResponse updateRole(RoleAddRequest request) {
        Role role = roleRepo.findRoleByRoleName(request.getRoleName())
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.RL_002, getMessageBundle(ResourceBundleConstant.RL_002)));

        if (roleRepo.existsByRoleName(request.getRoleName())) {
            throw new ApiRequestException(ResourceBundleConstant.RL_004, getMessageBundle(ResourceBundleConstant.RL_004));
        }

        if (!role.getRoleName().equals(request.getRoleName())) {
            role.setRoleName(request.getRoleName());
        }

        if (!role.getRoleCode().equals(request.getRoleCode())) {
            role.setRoleCode(request.getRoleCode());
        }

        Role roleSave = roleRepo.save(role);

        return RoleResponse.builder()
                .code(ResourceBundleConstant.RL_005)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(roleDtoMapper.apply(roleSave))
                .message(getMessageBundle(ResourceBundleConstant.RL_005))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public RoleResponse deleteRole(RoleAddRequest request) {
        return null;
    }

    @Override
    public RoleResponse findById(UUID roleId) {
        roleRepo.findById(roleId)
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.RL_002, getMessageBundle(ResourceBundleConstant.RL_002)));
        return null;
    }

    @Override
    public RoleResponses findAllRole() {
        List<Role> all = roleRepo.findAll();

        List<RoleDto> roleDtos = all.stream()
                .map(roleDtoMapper)
                .toList();

        return RoleResponses.builder()
                .code(ResourceBundleConstant.RL_011)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(roleDtos)
                .message(getMessageBundle(ResourceBundleConstant.RL_011))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }
}





