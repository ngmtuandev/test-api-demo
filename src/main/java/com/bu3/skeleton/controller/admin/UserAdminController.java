package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.UserConstant;
import com.bu3.skeleton.dto.request.user.UserAddRequest;
import com.bu3.skeleton.dto.request.user.UserLoginRequest;
import com.bu3.skeleton.dto.request.user.UserUpdateRequest;
import com.bu3.skeleton.dto.response.user.UserResponse;
import com.bu3.skeleton.dto.response.user.UserResponses;
import com.bu3.skeleton.sevice.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + UserConstant.API_USER)
@RequiredArgsConstructor
public class UserAdminController {

    private final IUserService userService;

    @GetMapping
    public ResponseEntity<UserResponses> findUsers(
            @RequestParam(value = "currentPage", required = false) Optional<Integer> currentPage,
            @RequestParam(value = "limitPage", required = false) Optional<Integer> limitPage
    ) {
        return new ResponseEntity<>(userService.findAllUser(currentPage.orElse(1), limitPage.orElse(8)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponse> addUser(
            @Valid @RequestBody UserAddRequest request
    ) {
        return new ResponseEntity<>(userService.addUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/authenticated")
    public ResponseEntity<UserResponse> authenticated(
            @Valid @RequestBody UserLoginRequest request
    ) {
        return new ResponseEntity<>(userService.authenticated(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest request) {
        return new ResponseEntity<>(userService.updateUser(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<UserResponse> deleteUser(@RequestParam("email") String email) {
        return new ResponseEntity<>(userService.deleteUser(email), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
