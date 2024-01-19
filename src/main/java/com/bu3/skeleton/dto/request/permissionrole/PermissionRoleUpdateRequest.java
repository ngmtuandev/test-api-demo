package com.bu3.skeleton.dto.request.permissionrole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PermissionRoleUpdateRequest {

    private UUID permissionRoleId;

    private UUID permissionId;

    private UUID roleId;

}
