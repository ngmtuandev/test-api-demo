package com.bu3.skeleton.dto.request.permissiongroup;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissionGroupRequest {

    @NotNull(message = "Permission group name not null")
    private String permissionGroupName;
    @NotNull(message = "Not null description")
    private String description;

}
