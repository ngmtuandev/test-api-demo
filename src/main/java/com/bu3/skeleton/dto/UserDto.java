package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public class UserDto {

    private UUID userId;

    private String email;

    private String gender;

    private LocalDate dateOfBirth;

    private String fullName;

    private String phoneNumber;

    private String address;

    private Boolean isDeleted;

    private String jwtToken;

    private List<RoleDto> roles;

    private List<PermissionDto> permissions;

    private List<PermissionGroupDto> permissionGroups;
}
