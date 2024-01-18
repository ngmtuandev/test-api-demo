package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.role.RoleAddRequest;
import com.bu3.skeleton.dto.response.role.RoleResponse;
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

    private final BaseAmenityUtil baseAmenity;

    private final String roleCode = Translator.toLocale(TransitionCode.ROLE_CODE);

    @Override
    public void addRole(RoleAddRequest request) {
        if (roleRepo.existsByRoleName(request.getRoleName())) {
            throw new ApiRequestException(Translator.toLocale(TransitionCode.ROLE_CODE),
                    Translator.toLocale(TransitionCode.ROLE_NAME_EXISTS));
        }

        roleRepo.save(
                Role.builder()
                        .roleName(request.getRoleName())
                        .roleCode(request.getRoleCode())
                        .isDeleted(SystemConstant.ACTIVE)
                        .build()
        );
    }

    @Override
    public void updateRole(RoleAddRequest request) {
        Role role = roleRepo.findRoleByRoleName(request.getRoleName())
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.ROLE_CODE),
                        Translator.toLocale(TransitionCode.ROLE_NAME_EXISTS)));

        if (roleRepo.existsByRoleName(request.getRoleName())) {
            throw new ApiRequestException(Translator.toLocale(TransitionCode.ROLE_CODE),
                    Translator.toLocale(TransitionCode.ROLE_NAME_EXISTS));
        }

        if (roleRepo.existsByRoleName(request.getRoleName())) {
            throw new ApiRequestException(Translator.toLocale(TransitionCode.ROLE_CODE),
                    Translator.toLocale(TransitionCode.ROLE_NAME_EXISTS));
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
    public RoleResponse findById(UUID roleId) {
        roleRepo.findById(roleId)
                .orElseThrow(() -> new ApiRequestException(roleCode,
                        Translator.toLocale(TransitionCode.NOT_FOUND)));
        return null;
    }

    @Override
    public List<RoleDto> findAllRole() {
        List<Role> all = roleRepo.findAll();

        return all.stream()
                .map(roleDtoMapper)
                .toList();
    }
}





