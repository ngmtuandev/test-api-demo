package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class RoleDto {

    private UUID roleId;

    private String roleName;

    private String roleCode;

    private Boolean isDeleted;

}
