package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.PermissionDto;
import com.bu3.skeleton.dto.request.PermissionRequest;
import com.bu3.skeleton.dto.request.PermissionUpdateRequest;
import com.bu3.skeleton.dto.response.PermissionResponses;
import com.bu3.skeleton.entity.Permission;
import com.bu3.skeleton.entity.PermissionGroup;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.PermissionDtoMapper;
import com.bu3.skeleton.repository.IPermissionGroupRepo;
import com.bu3.skeleton.repository.IPermissionRepo;
import com.bu3.skeleton.sevice.IPermissionService;
import com.bu3.skeleton.util.BaseAmenity;
import com.bu3.skeleton.util.PageableResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionServiceImpl implements IPermissionService {

    private final IPermissionRepo permissionRepo;

    private final PermissionDtoMapper permissionDtoMapper;

    private final IPermissionGroupRepo permissionGroupRepo;

    private final BaseAmenity baseAmenity;


    private Permission getPermission(UUID permissionId) {
        return permissionRepo.findById(permissionId)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.PERMISSION_CODE),
                        Translator.toLocale(TransitionCode.PERMISSION_FIND_NOT_FOUND)));
    }

    private PermissionGroup getPermissionGroup(UUID permissionGroupId) {
        return permissionGroupRepo.findById(permissionGroupId)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.PERMISSION_GROUP_CODE),
                        Translator.toLocale(TransitionCode.PERMISSION_GROUP_FIND_BY_ID_NOT_FOUND)));
    }

    @Override
    public void addPermission(PermissionRequest request) {
        PermissionGroup permissionGroup = getPermissionGroup(request.getPermissionGroupId());

        permissionRepo.save(
                Permission.builder()
                        .permissionGroup(permissionGroup)
                        .permissionCode(request.getPermissionCode())
                        .build()
        );
    }

    @Override
    public void updatePermission(PermissionUpdateRequest request) {
        PermissionGroup permissionGroup = getPermissionGroup(request.getPermissionGroupId());

        Permission permission = getPermission(request.getPermissionId());

        if (!permission.getPermissionGroup().equals(permissionGroup)) {
            permission.setPermissionGroup(permissionGroup);
        }

        if (!permission.getPermissionCode().equals(request.getPermissionCode())) {
            permission.setPermissionCode(request.getPermissionCode());
        }

        if (!permission.getDescription().equals(request.getDescription())) {
            permission.setDescription(request.getDescription());
        }

        permissionRepo.save(permission);
    }

    @Override
    public void deletePermission(UUID permissionId) {
        Permission permission = getPermission(permissionId);
        permission.setIsDeleted(false);
        permissionRepo.save(permission);
    }

    @Override
    public PermissionResponses findAllPermission(Integer currentPage, Integer limitPage) {
        Pageable pageable = baseAmenity.pageable(currentPage,limitPage);
        Page<Permission> all = permissionRepo.findAll(pageable);

        List<PermissionDto> permissionDtos = all.stream()
                .map(permissionDtoMapper)
                .toList();

        PageableResponse pageableResponse = baseAmenity.pageableResponse(currentPage, limitPage, all.getTotalPages());

        return PermissionResponses.builder()
                .code(Translator.toLocale(TransitionCode.PERMISSION_CODE))
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionDtos)
                .meta(pageableResponse)
                .message(Translator.toLocale(TransitionCode.PERMISSION_SUCCESS))
                .responseTime(baseAmenity.currentTimeSeconds())
                .build();
    }
}
