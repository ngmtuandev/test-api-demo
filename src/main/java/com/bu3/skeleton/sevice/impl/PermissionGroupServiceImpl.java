package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.constant.ResourceBundleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.PermissionGroupDto;
import com.bu3.skeleton.dto.request.permissiongroup.PermissionGroupRequest;
import com.bu3.skeleton.dto.response.PageableResponse;
import com.bu3.skeleton.dto.response.permissiongroup.PermissionGroupResponse;
import com.bu3.skeleton.dto.response.permissiongroup.PermissionGroupResponses;
import com.bu3.skeleton.entity.PermissionGroup;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.PermissionGroupDtoMapper;
import com.bu3.skeleton.repository.IPermissionGroupRepo;
import com.bu3.skeleton.sevice.IPermissionGroupService;
import com.bu3.skeleton.util.BaseAmenityUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionGroupServiceImpl implements IPermissionGroupService {

    private final IPermissionGroupRepo permissionGroupRepo;

    private final RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper;

    private final BaseAmenityUtil baseAmenityUtil;

    private final PermissionGroupDtoMapper permissionGroupDtoMapper;
    private final Logger logger = LoggerFactory.getLogger(PermissionGroupServiceImpl.class);

    private String getMessageBundle(String key) {
        return baseAmenityUtil.getMessageBundle(key);
    }

    @Override
    public PermissionGroupResponse addPermissionGroup(PermissionGroupRequest request) {
        if (permissionGroupRepo.existsByPermissionGroupName(request.getPermissionGroupName())) {
            throw new ApiRequestException(ResourceBundleConstant.PMSG_011, getMessageBundle(ResourceBundleConstant.PMSG_011));
        }

        PermissionGroup permissionGroup = permissionGroupRepo.save(
                PermissionGroup.builder()
                        .permissionGroupName(request.getPermissionGroupName())
                        .description(request.getDescription())
                        .isDeleted(SystemConstant.ACTIVE)
                        .build()
        );

        return PermissionGroupResponse.builder()
                .code(ResourceBundleConstant.PMSG_005)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionGroupDtoMapper.apply(permissionGroup))
                .message(getMessageBundle(ResourceBundleConstant.PMSG_005))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public PermissionGroupResponse updatePermissionGroup(PermissionGroupRequest request) {
        if (permissionGroupRepo.existsByPermissionGroupNameNot(request.getPermissionGroupName())) {
            throw new ApiRequestException(ResourceBundleConstant.PMSG_011, getMessageBundle(ResourceBundleConstant.PMSG_011));
        }

        PermissionGroup permissionGroup = getPermissionGroup(request.getPermissionGroupName());

        if (!permissionGroup.getPermissionGroupName().equals(request.getPermissionGroupName())) {
            permissionGroup.setPermissionGroupName(request.getPermissionGroupName());
        }

        if (!permissionGroup.getDescription().equals(request.getDescription())) {
            permissionGroup.setDescription(request.getDescription());
        }

        PermissionGroup permissionGroupSave = permissionGroupRepo.save(permissionGroup);

        return PermissionGroupResponse.builder()
                .code(ResourceBundleConstant.PMSG_003)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionGroupDtoMapper.apply(permissionGroupSave))
                .message(getMessageBundle(ResourceBundleConstant.PMSG_003))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    private PermissionGroup getPermissionGroup(String permissionGroupName) {
        return permissionGroupRepo.findPermissionGroupByPermissionGroupName(permissionGroupName)
                .orElseThrow(() -> new ApiRequestException(ResourceBundleConstant.PMSG_002, getMessageBundle(ResourceBundleConstant.PMSG_002)));
    }

    @Override
    public PermissionGroupResponse deletePermissionGroup(String permissionGroupName) {
        if (permissionGroupRepo == null) {
            throw new ApiRequestException(ResourceBundleConstant.PMSG_002, getMessageBundle(ResourceBundleConstant.PMSG_002));
        }
        PermissionGroup permissionGroup = getPermissionGroup(permissionGroupName);
        permissionGroup.setIsDeleted(false);
        PermissionGroup permissionGroupSave = permissionGroupRepo.save(permissionGroup);

        return PermissionGroupResponse.builder()
                .code(ResourceBundleConstant.PMSG_007)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionGroupDtoMapper.apply(permissionGroupSave))
                .message(getMessageBundle(ResourceBundleConstant.PMSG_007))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();
    }

    @Override
    public PermissionGroupResponses findAllPermissionGroup(int currentPage, int limitPage) {
        Pageable pageable = baseAmenityUtil.pageable(currentPage, limitPage);
        Page<PermissionGroup> all = permissionGroupRepo.findAll(pageable);
//        try {
//            redisTemplate.opsForValue().getOperations().delete("ca");
//            if (redisTemplate.opsForValue().get("ca") != null) {
//                String jsonData = redisTemplate.opsForValue().get("ca");
//                System.out.println("Cache ne!");
//
//                List<PermissionGroup> permissionGroups = objectMapper.readValue(
//                        jsonData,
//                        new TypeReference<>() {
//                        }
//                );
//
//                return permissionGroups.stream()
//                        .map(permissionGroupDtoMapper)
//                        .toList();
//            } else {
//                System.out.println("No Cache!");
//                all = permissionGroupRepo.findAll();
//
//                String valueAsString = objectMapper.writeValueAsString(all);
//                redisTemplate.opsForValue().set("ca", valueAsString);
//            }
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
        List<PermissionGroupDto> permissionGroupDtos = all.stream()
                .map(permissionGroupDtoMapper)
                .toList();

        PageableResponse pageableResponse = baseAmenityUtil.pageableResponse(currentPage, limitPage, all.getTotalPages());

        return PermissionGroupResponses.builder()
                .code(ResourceBundleConstant.PMSG_009)
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionGroupDtos)
                .meta(pageableResponse)
                .message(getMessageBundle(ResourceBundleConstant.PMSG_009))
                .responseTime(baseAmenityUtil.currentTimeSeconds())
                .build();

    }
}
