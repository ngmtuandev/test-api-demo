package com.bu3.skeleton.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleRequest {

    private String roleName;

    private String email;
}
