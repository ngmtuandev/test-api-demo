package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;
import com.bu3.skeleton.dto.request.UserUpdateRequest;
import com.bu3.skeleton.dto.response.UsersResponse;

public interface IUserService {

    void addUser(UserAddRequest request);

    void updateUser(UserUpdateRequest request);

    void deleteUser(String email);

    UsersResponse findAllUser(Integer currentPage, Integer limitPage);

    UserDto authenticated(UserLoginRequest request);
}
