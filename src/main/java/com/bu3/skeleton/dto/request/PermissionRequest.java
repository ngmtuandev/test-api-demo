package com.bu3.skeleton.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PermissionRequest {

    private String permissionCode;

    private UUID permissionGroupId;
}
