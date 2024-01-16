package com.bu3.skeleton.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissionRoleRequest {

    private String permissionCode;

    private String roleName;
}
