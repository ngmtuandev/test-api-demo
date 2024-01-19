package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.userrole.UserRoleAddRequest;
import com.bu3.skeleton.dto.request.userrole.UserRoleUpdateRequest;
import com.bu3.skeleton.dto.response.userrole.UserRoleResponse;
import com.bu3.skeleton.dto.response.userrole.UserRoleResponses;

import java.util.UUID;

public interface IUserRoleService {

    UserRoleResponse addUserRole(UserRoleAddRequest request);

    UserRoleResponse updateUserRole(UserRoleUpdateRequest request);

    UserRoleResponse deleteUserRole(UUID userRoleId);

    UserRoleResponses findAllUserRole(Integer currentPage, Integer limitPage);
}
