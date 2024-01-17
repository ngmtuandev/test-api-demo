package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.UserAddRequest;
import com.bu3.skeleton.dto.request.UserLoginRequest;
import com.bu3.skeleton.dto.request.UserUpdateRequest;
import com.bu3.skeleton.dto.response.UserResponse;
import com.bu3.skeleton.dto.response.UserResponses;
import com.bu3.skeleton.sevice.IUserService;
import com.bu3.skeleton.validation.IValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + SystemConstant.API_USER)
@RequiredArgsConstructor
public class UserAdminController {

    private final IUserService userService;

    private final IValidationService validationService;

    @GetMapping
    public ResponseEntity<UserResponses> findUsers(
            @RequestParam(value = "current-page", required = false) Optional<Integer> currentPage,
            @RequestParam(value = "limit-page", required = false) Optional<Integer> limitPage
    ) {
        return new ResponseEntity<>(userService.findAllUser(currentPage.orElse(1), limitPage.orElse(8)), HttpStatus.OK);
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
    public ResponseEntity<UserResponse> authenticated(
            @Validated @RequestBody UserLoginRequest request,
            Errors errors
    ) {
        validationService.handleValidate(errors);
        return new ResponseEntity<>(userService.authenticated(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@Validated @RequestBody UserUpdateRequest request, Errors errors) {
        validationService.handleValidate(errors);
        userService.updateUser(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestParam("email") String email) {
        userService.deleteUser(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
