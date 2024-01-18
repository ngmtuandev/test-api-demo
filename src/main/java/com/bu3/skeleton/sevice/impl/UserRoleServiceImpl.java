package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.UserRoleDto;
import com.bu3.skeleton.dto.request.userrole.UserRoleAddRequest;
import com.bu3.skeleton.dto.request.userrole.UserRoleUpdateRequest;
import com.bu3.skeleton.dto.response.PageableResponse;
import com.bu3.skeleton.dto.response.userrole.UserRoleResponse;
import com.bu3.skeleton.dto.response.userrole.UserRoleResponses;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.entity.User;
import com.bu3.skeleton.entity.UserRole;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.UserRoleDtoMapper;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.repository.IUserRepo;
import com.bu3.skeleton.repository.IUserRoleRepo;
import com.bu3.skeleton.sevice.IUserRoleService;
import com.bu3.skeleton.util.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserRoleServiceImpl implements IUserRoleService {

    private final IUserRoleRepo userRoleRepo;

    private final IRoleRepo roleRepo;

    private final IUserRepo userRepo;

    private final UserRoleDtoMapper userRoleDtoMapper;

    private final BaseAmenityUtil baseAmenityUtil;

    private final String userRoleCode = Translator.toLocale(TransitionCode.USER_ROLE_CODE);

    private User getUser(String email) {
        return userRepo.findUserByEmailAndIsDeleted(email, SystemConstant.ACTIVE)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.USER_CODE),
                        Translator.toLocale(TransitionCode.USER_FIND_NOT_FOUND)));
    }

    private Role getRole(UUID roleId) {
        return roleRepo.findById(roleId)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.ROLE_CODE),
                        Translator.toLocale(TransitionCode.FIND_ROLE_BY_ROLE_NAME_NOT_FOUND)));
    }

    @Override
    public UserRoleResponse addUserRole(UserRoleAddRequest request) {
        Role role = getRole(request.getRoleId());

        User user = getUser(request.getEmail());

        UserRole userRole = userRoleRepo.save(
                UserRole.builder()
                        .role(role)
                        .user(user)
                        .isDeleted(SystemConstant.ACTIVE)
                        .build()
        );

        return UserRoleResponse.builder()
                .code(userRoleCode)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userRoleDtoMapper.apply(userRole))
                .message(Translator.toLocale(TransitionCode.ADD_SUCCESS))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserRoleResponse updateUserRole(UserRoleUpdateRequest request) {
        UserRole userRole = userRoleRepo.findById(request.getUserRoleId())
                .orElseThrow(() -> new ApiRequestException(userRoleCode, Translator.toLocale(TransitionCode.NOT_FOUND)));

        Role role = getRole(request.getRoleId());
        User user = getUser(request.getEmail());

        userRole.setRole(role);
        userRole.setUser(user);

        userRoleRepo.save(userRole);
        return UserRoleResponse.builder()
                .code(userRoleCode)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userRoleDtoMapper.apply(userRole))
                .message(Translator.toLocale(TransitionCode.UPDATE_SUCCESS))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserRoleResponse deleteUserRole(UUID userRoleId) {
        UserRole userRole = userRoleRepo.findById(userRoleId)
                .orElseThrow(() -> new ApiRequestException(userRoleCode, Translator.toLocale(TransitionCode.USER_ROLE_NOT_FOUND)));

        userRole.setIsDeleted(SystemConstant.NO_ACTIVE);
        UserRole userRoleSave = userRoleRepo.save(userRole);

        return UserRoleResponse.builder()
                .code(userRoleCode)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userRoleDtoMapper.apply(userRoleSave))
                .message(Translator.toLocale(TransitionCode.DELETE_SUCCESS))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public UserRoleResponses findAllUserRole(Integer currentPage, Integer limitPage) {
        Pageable pageable = baseAmenityUtil.pageable(currentPage, limitPage);
        Page<UserRole> all = userRoleRepo.findAll(pageable);

        List<UserRoleDto> userRoleDtos = all.stream()
                .map(userRoleDtoMapper)
                .toList();

        PageableResponse pageableResponse = baseAmenityUtil.pageableResponse(currentPage, limitPage, all.getTotalPages());

        return UserRoleResponses.builder()
                .code(userRoleCode)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(userRoleDtos)
                .meta(pageableResponse)
                .message(Translator.toLocale(TransitionCode.GET_SUCCESS))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }
}
