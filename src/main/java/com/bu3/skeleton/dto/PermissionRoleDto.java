package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class PermissionRoleDto {

    private UUID permissionRoleId;

    private UUID permissionId;

    private String permissionCode;

    private UUID roleId;

    private String roleName;
}
