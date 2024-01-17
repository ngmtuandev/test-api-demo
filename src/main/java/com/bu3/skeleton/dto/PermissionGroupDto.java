package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PermissionGroupDto {

    private UUID permissionGroupId;

    private String permissionGroupName;

    private Boolean isDeleted;

    private String description;
}
