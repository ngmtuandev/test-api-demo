package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;
import com.bu3.skeleton.dto.response.UsersResponse;
import com.bu3.skeleton.sevice.IUserService;
import com.bu3.skeleton.util.BasesResponse;
import com.bu3.skeleton.validation.IValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + SystemConstant.API_USER)
@RequiredArgsConstructor
public class UserAdminController {

    private final IUserService userService;

    private final IValidationService validationService;

    @GetMapping
    public ResponseEntity<UsersResponse> findUsers() {
        return new ResponseEntity<>(userService.findAllUser(1,2), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(
            @Validated @RequestBody UserAddRequest request,
            Errors errors
    ) {
        validationService.handleValidate(errors);
        userService.addUser(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/authenticated")
    public ResponseEntity<UserDto> authenticated(
            @Validated @RequestBody UserLoginRequest request,
            Errors errors
    ) {
        validationService.handleValidate(errors);
        return new ResponseEntity<>(userService.authenticated(request), HttpStatus.OK);
    }
}
