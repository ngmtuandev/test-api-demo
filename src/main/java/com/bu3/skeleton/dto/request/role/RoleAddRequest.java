package com.bu3.skeleton.dto.request.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RoleAddRequest {

    private String roleName;

    private String roleCode;
}
