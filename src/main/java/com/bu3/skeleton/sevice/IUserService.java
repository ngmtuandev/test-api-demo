package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;
import com.bu3.skeleton.dto.request.UserUpdateRequest;
import com.bu3.skeleton.dto.response.UserResponse;
import com.bu3.skeleton.dto.response.UserResponses;

public interface IUserService {

    void addUser(UserAddRequest request);

    void updateUser(UserUpdateRequest request);

    void deleteUser(String email);

    UserResponses findAllUser(Integer currentPage, Integer limitPage);

    UserResponse authenticated(UserLoginRequest request);
}
