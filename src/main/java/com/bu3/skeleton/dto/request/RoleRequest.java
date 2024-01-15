package com.bu3.skeleton.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleRequest {

    @NotNull
    private String roleName;

    private String roleCode;
}
