package com.bu3.skeleton.controller.publicapi;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.UserConstant;
import com.bu3.skeleton.dto.request.user.UserLoginRequest;
import com.bu3.skeleton.dto.response.user.UserResponse;
import com.bu3.skeleton.sevice.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_1 + UserConstant.API_USER)
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @PostMapping(UserConstant.AUTHENTICATED)
    public ResponseEntity<UserResponse> authenticated(
            @Valid @RequestBody UserLoginRequest request
    ) {
        return new ResponseEntity<>(userService.authenticated(request), HttpStatus.OK);
    }

    @GetMapping(SystemConstant.API_USER_LOGOUT)
    public ResponseEntity<?> logoutUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
