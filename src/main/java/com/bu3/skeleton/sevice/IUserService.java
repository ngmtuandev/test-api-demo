package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;

import java.util.List;

public interface IUserService {

    void addUser(UserAddRequest request);

    List<UserDto> findAllUser();

    UserDto authenticated(UserLoginRequest request);
}
