package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class UserRoleDto {

    private UUID userRoleId;

    private String roleName;

    private String email;

    private String fullName;

    private Boolean isDeleted;
}
