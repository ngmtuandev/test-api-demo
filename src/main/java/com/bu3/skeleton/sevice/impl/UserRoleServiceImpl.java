package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.UserRoleDto;
import com.bu3.skeleton.dto.request.UserRoleRequest;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.entity.User;
import com.bu3.skeleton.entity.UserRole;
import com.bu3.skeleton.exception.ResourceNotFoundException;
import com.bu3.skeleton.mapper.UserRoleDtoMapper;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.repository.IUserRepo;
import com.bu3.skeleton.repository.IUserRoleRepo;
import com.bu3.skeleton.sevice.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleServiceImpl implements IUserRoleService {

    private final IUserRoleRepo userRoleRepo;

    private final IRoleRepo roleRepo;

    private final IUserRepo userRepo;

    private final UserRoleDtoMapper userRoleDtoMapper;

    @Override
    public void addUserRole(UserRoleRequest request) {
        Role role = roleRepo.findRoleByRoleName(request.getRoleName())
                .orElseThrow(() -> new ResourceNotFoundException("find role by role name not found!"));

        User user = userRepo.findUserByEmailAndStatus(request.getEmail(), SystemConstant.USER_ACTIVE)
                .orElseThrow(() -> new ResourceNotFoundException("find user by email not found!"));

        userRoleRepo.save(
                UserRole.builder()
                        .role(role)
                        .user(user)
                        .build()
        );
    }

    @Override
    public List<UserRoleDto> findAllUserRole() {
        List<UserRole> all = userRoleRepo.findAll();

        return all.stream()
                .map(userRoleDtoMapper)
                .toList();
    }
}
