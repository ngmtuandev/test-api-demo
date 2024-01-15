package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.RoleRequest;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.exception.ResourceDuplicateException;
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
            throw new ResourceDuplicateException("duplicate!");
        }

        roleRepo.save(
                Role.builder()
                        .roleName(request.getRoleName())
                        .roleCode(request.getRoleCode())
                        .build()
        );
    }

    @Override
    public void updateRole(RoleRequest request) {

    }

    @Override
    public List<RoleDto> findAllRole() {
        List<Role> all = roleRepo.findAll();

        return all.stream()
                .map(roleDtoMapper)
                .toList();
    }
}
