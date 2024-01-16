package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.PermissionDto;
import com.bu3.skeleton.dto.request.PermissionRequest;
import com.bu3.skeleton.entity.Permission;
import com.bu3.skeleton.entity.PermissionGroup;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.PermissionDtoMapper;
import com.bu3.skeleton.repository.IPermissionGroupRepo;
import com.bu3.skeleton.repository.IPermissionRepo;
import com.bu3.skeleton.sevice.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionServiceImpl implements IPermissionService {

    private final IPermissionRepo permissionRepo;

    private final PermissionDtoMapper permissionDtoMapper;

    private final IPermissionGroupRepo permissionGroupRepo;

    @Override
    public void addPermission(PermissionRequest request) {
        PermissionGroup permissionGroup = permissionGroupRepo.findById(request.getPermissionGroupId())
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.PERMISSION_GROUP_FIND_BY_ID_NOT_FOUND)));

        permissionRepo.save(
                Permission.builder()
                        .permissionGroup(permissionGroup)
                        .permissionCode(request.getPermissionCode())
                        .build()
        );
    }

    @Override
    public List<PermissionDto> findAllPermission() {
        List<Permission> all = permissionRepo.findAll();

        return all.stream()
                .map(permissionDtoMapper)
                .toList();
    }
}
