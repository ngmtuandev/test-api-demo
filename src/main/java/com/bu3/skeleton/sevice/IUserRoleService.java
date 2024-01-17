package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.UserRoleDto;
import com.bu3.skeleton.dto.request.UserRoleRequest;

import java.util.List;

public interface IUserRoleService {

    void addUserRole(UserRoleRequest request);

    List<UserRoleDto> findAllUserRole();
}
