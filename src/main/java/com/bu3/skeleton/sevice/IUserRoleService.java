package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.UserRoleRequest;
import com.bu3.skeleton.dto.response.UserRoleResponses;

public interface IUserRoleService {

    void addUserRole(UserRoleRequest request);

    void deleteUserRole(UserRoleRequest request);

    UserRoleResponses findAllUserRole();
}
