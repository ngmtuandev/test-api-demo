package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.user.UserAddRequest;
import com.bu3.skeleton.dto.request.user.UserLoginRequest;
import com.bu3.skeleton.dto.request.user.UserUpdateRequest;
import com.bu3.skeleton.dto.response.user.UserResponse;
import com.bu3.skeleton.dto.response.user.UserResponses;

public interface IUserService {

    UserResponse addUser(UserAddRequest request);

    UserResponse updateUser(UserUpdateRequest request);

    UserResponse deleteUser(String email);

    UserResponses findAllUser(Integer currentPage, Integer limitPage);
    UserResponses findUsersByIsDeleted(Integer currentPage, Integer limitPage, Boolean isDeleted);

    UserResponse authenticated(UserLoginRequest request);
}
