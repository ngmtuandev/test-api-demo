package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PermissionDto {

    private UUID permissionId;

    private String permissionCode;

    private Boolean isDeleted;

    private String description;

}
