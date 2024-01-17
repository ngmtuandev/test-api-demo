package com.bu3.skeleton.sevice.impl;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.dto.PermissionGroupDto;
import com.bu3.skeleton.dto.request.PermissionGroupRequest;
import com.bu3.skeleton.dto.response.PermissionGroupResponses;
import com.bu3.skeleton.entity.PermissionGroup;
import com.bu3.skeleton.exception.ApiRequestException;
import com.bu3.skeleton.mapper.PermissionGroupDtoMapper;
import com.bu3.skeleton.repository.IPermissionGroupRepo;
import com.bu3.skeleton.sevice.IPermissionGroupService;
import com.bu3.skeleton.util.BaseAmenity;
import com.bu3.skeleton.util.PageableResponse;
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

    private final BaseAmenity baseAmenity;

    private final PermissionGroupDtoMapper permissionGroupDtoMapper;

    private final Logger logger = LoggerFactory.getLogger(PermissionGroupServiceImpl.class);


    @Override
    public void addPermissionGroup(PermissionGroupRequest request) {
        if (permissionGroupRepo.existsByPermissionGroupName(request.getPermissionGroupName())) {
            throw new ApiRequestException(Translator.toLocale(TransitionCode.PERMISSION_GROUP_CODE),
                    Translator.toLocale(TransitionCode.PERMISSION_GROUP_EXISTS));
        }

        permissionGroupRepo.save(
                PermissionGroup.builder()
                        .permissionGroupName(request.getPermissionGroupName())
                        .description(request.getDescription())
                        .isDeleted(SystemConstant.PERMISSION_GROUP_ACTIVE)
                        .build()
        );
    }

    @Override
    public void updatePermissionGroup(PermissionGroupRequest request) {
        if (permissionGroupRepo.existsByPermissionGroupNameNot(request.getPermissionGroupName())) {
            throw new ApiRequestException(Translator.toLocale(TransitionCode.PERMISSION_GROUP_CODE), Translator.toLocale(TransitionCode.PERMISSION_GROUP_EXISTS));
        }

        PermissionGroup permissionGroup = getPermissionGroup(request.getPermissionGroupName());

        if (!permissionGroup.getPermissionGroupName().equals(request.getPermissionGroupName())) {
            permissionGroup.setPermissionGroupName(request.getPermissionGroupName());
        }

        if (!permissionGroup.getDescription().equals(request.getDescription())) {
            permissionGroup.setDescription(request.getDescription());
        }

        permissionGroup.setIsDeleted(request.getIsDeleted());

        permissionGroupRepo.save(permissionGroup);
    }

    private PermissionGroup getPermissionGroup(String permissionGroupName) {
        return permissionGroupRepo.findPermissionGroupByPermissionGroupName(permissionGroupName)
                .orElseThrow(() -> new ApiRequestException(Translator.toLocale(TransitionCode.PERMISSION_GROUP_CODE),
                        Translator.toLocale(TransitionCode.PERMISSION_GROUP_EXISTS)));
    }

    @Override
    public void deletePermissionGroup(String permissionGroupName) {
        if (permissionGroupRepo == null) {
            throw new ApiRequestException(Translator.toLocale(TransitionCode.PERMISSION_GROUP_CODE),
                    Translator.toLocale(TransitionCode.NOT_FOUND));
        }
        PermissionGroup permissionGroup = getPermissionGroup(permissionGroupName);
        permissionGroup.setIsDeleted(false);
        permissionGroupRepo.save(permissionGroup);
    }

    @Override
    public PermissionGroupResponses findAllPermissionGroup(int currentPage, int limitPage) {
        Pageable pageable = baseAmenity.pageable(currentPage, limitPage);
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

        PageableResponse pageableResponse = baseAmenity.pageableResponse(currentPage, limitPage, all.getTotalPages());

        return PermissionGroupResponses.builder()
                .code(baseAmenity.getMessageNotification(TransitionCode.PERMISSION_GROUP_CODE))
                .status(SystemConstant.STATUS_CODE_SUCCESS)
                .data(permissionGroupDtos)
                .meta(pageableResponse)
                .message(Translator.toLocale(TransitionCode.PERMISSION_GROUP_FIND_SUCCESS))
                .responseTime(baseAmenity.currentTimeSeconds())
                .build();

    }
}
