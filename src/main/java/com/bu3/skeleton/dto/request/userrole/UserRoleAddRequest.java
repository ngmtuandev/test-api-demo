package com.bu3.skeleton.dto.request.userrole;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserRoleAddRequest {

    private UUID roleId;

    private String email;
}
