package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.constant.ResourceBundleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.PermissionDto;
import com.bu3.skeleton.dto.request.permission.PermissionRequest;
import com.bu3.skeleton.dto.request.permission.PermissionUpdateRequest;
import com.bu3.skeleton.dto.response.PageableResponse;
import com.bu3.skeleton.dto.response.permission.PermissionResponse;
import com.bu3.skeleton.dto.response.permission.PermissionResponses;
import com.bu3.skeleton.entity.Permission;
import com.bu3.skeleton.entity.PermissionGroup;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.PermissionDtoMapper;
import com.bu3.skeleton.repository.IPermissionGroupRepo;
import com.bu3.skeleton.repository.IPermissionRepo;
import com.bu3.skeleton.sevice.IPermissionService;
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
public class PermissionServiceImpl implements IPermissionService {

    private final IPermissionRepo permissionRepo;

    private final PermissionDtoMapper permissionDtoMapper;

    private final IPermissionGroupRepo permissionGroupRepo;

    private final BaseAmenityUtil baseAmenityUtil;

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    private Permission getPermission(UUID permissionId) {
        return permissionRepo.findById(permissionId)
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.PMS_002, getMessageBundle(ResourceBundleConstant.PMS_002)));
    }

    private PermissionGroup getPermissionGroup(UUID permissionGroupId) {
        return permissionGroupRepo.findById(permissionGroupId)
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.PMSG_002, getMessageBundle(ResourceBundleConstant.PMSG_002)));
    }

    @Override
    public PermissionResponse addPermission(PermissionRequest request) {
        PermissionGroup permissionGroup = getPermissionGroup(request.getPermissionGroupId());

        Permission permission = permissionRepo.save(
                Permission.builder()
                        .permissionGroup(permissionGroup)
                        .permissionCode(request.getPermissionCode())
                        .isDeleted(SystemConstant.ACTIVE)
                        .build()
        );

        return PermissionResponse.builder()
                .code(ResourceBundleConstant.PMS_003)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionDtoMapper.apply(permission))
                .message(getMessageBundle(ResourceBundleConstant.PMS_003))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public PermissionResponse updatePermission(PermissionUpdateRequest request) {
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

        Permission permissionSave = permissionRepo.save(permission);

        return PermissionResponse.builder()
                .code(ResourceBundleConstant.PMS_005)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionDtoMapper.apply(permissionSave))
                .message(getMessageBundle(ResourceBundleConstant.PMS_005))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public PermissionResponse deletePermission(UUID permissionId) {
        Permission permission = getPermission(permissionId);
        permission.setIsDeleted(SystemConstant.NO_ACTIVE);
        Permission permissionSave = permissionRepo.save(permission);

        return PermissionResponse.builder()
                .code(ResourceBundleConstant.PMS_007)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionDtoMapper.apply(permissionSave))
                .message(getMessageBundle(ResourceBundleConstant.PMS_007))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public PermissionResponses findAllPermission(Integer currentPage, Integer limitPage) {
        Pageable pageable = baseAmenityUtil.pageable(currentPage, limitPage);
        Page<Permission> all = permissionRepo.findAll(pageable);

        List<PermissionDto> permissionDtos = all.stream()
                .map(permissionDtoMapper)
                .toList();

        PageableResponse pageableResponse = baseAmenityUtil.pageableResponse(currentPage, limitPage, all.getTotalPages());

        return PermissionResponses.builder()
                .code(ResourceBundleConstant.PMS_009)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionDtos)
                .meta(pageableResponse)
                .message(getMessageBundle(ResourceBundleConstant.PMS_009))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }
}
