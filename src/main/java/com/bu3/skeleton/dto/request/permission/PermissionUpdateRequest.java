package com.bu3.skeleton.dto.request.permission;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PermissionUpdateRequest {

    private UUID permissionGroupId;

    private UUID permissionId;

    private String permissionCode;

    private String description;
}
