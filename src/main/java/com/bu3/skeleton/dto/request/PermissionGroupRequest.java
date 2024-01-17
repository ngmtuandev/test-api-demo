package com.bu3.skeleton.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissionGroupRequest {

    private String permissionGroupName;

    private String description;

}
