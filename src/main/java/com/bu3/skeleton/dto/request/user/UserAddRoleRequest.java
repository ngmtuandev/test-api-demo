package com.bu3.skeleton.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserAddRoleRequest {

    private List<UUID> roleIds;

    private String email;
}
