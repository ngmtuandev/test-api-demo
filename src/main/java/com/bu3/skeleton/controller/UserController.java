package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.UserDto;
import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;
import com.bu3.skeleton.sevice.IUserService;
import com.bu3.skeleton.validation.IValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SystemConstant.API_USER + SystemConstant.VERSION_1)
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    private final IValidationService validationService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findUsers() {
        return new ResponseEntity<>(userService.findAllUser(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@RequestBody UserAddRequest request) {
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
