package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.UserRoleDto;
import com.bu3.skeleton.dto.request.UserRoleRequest;
import com.bu3.skeleton.dto.response.UserRoleResponses;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.entity.User;
import com.bu3.skeleton.entity.UserRole;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.UserRoleDtoMapper;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.repository.IUserRepo;
import com.bu3.skeleton.repository.IUserRoleRepo;
import com.bu3.skeleton.sevice.IUserRoleService;
import com.bu3.skeleton.util.BaseAmenity;
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

    private final BaseAmenity baseAmenity;

    @Override
    public void addUserRole(UserRoleRequest request) {
        Role role = roleRepo.findRoleByRoleName(request.getRoleName())
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.ROLE_CODE),
                        Translator.toLocale(TransitionCode.FIND_ROLE_BY_ROLE_NAME_NOT_FOUND)));

        User user = userRepo.findUserByEmailAndStatus(request.getEmail(), SystemConstant.USER_ACTIVE)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.USER_CODE),
                        Translator.toLocale(TransitionCode.USER_FIND_NOT_FOUND)));

        userRoleRepo.save(
                UserRole.builder()
                        .role(role)
                        .user(user)
                        .build()
        );
    }

    @Override
    public void deleteUserRole(UserRoleRequest request) {
        UserRole userRole = userRoleRepo.findUserRoleByRoleNameAndEmail(request.getRoleName(), request.getEmail())
                .orElseThrow(() -> new ApiRequestException(baseAmenity.getMessageNotification(TransitionCode.PERMISSION_CODE),
                        baseAmenity.getMessageNotification(TransitionCode.USER_ROLE_NOT_FOUND)));

        userRoleRepo.delete(userRole);
    }

    @Override
    public UserRoleResponses findAllUserRole() {
        List<UserRole> all = userRoleRepo.findAll();

        List<UserRoleDto> userRoleDtos = all.stream()
                .map(userRoleDtoMapper)
                .toList();

        return UserRoleResponses.builder()
                .code(baseAmenity.getMessageNotification(TransitionCode.USER_ROLE_CODE))
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userRoleDtos)
                .build();
    }
}
