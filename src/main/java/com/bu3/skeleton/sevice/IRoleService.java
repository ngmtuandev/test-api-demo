package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.RoleRequest;

import java.util.List;

public interface IRoleService {

    void addRole(RoleRequest request);

    List<RoleDto> findAllRole();
}
