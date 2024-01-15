package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.UserRoleDto;
import com.bu3.skeleton.dto.request.UserRoleRequest;
import com.bu3.skeleton.sevice.IUserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(SystemConstant.API_USER_ROLE + SystemConstant.VERSION_1)
@RequiredArgsConstructor
public class UserRoleController {

    private final IUserRoleService userRoleService;

    @GetMapping
    public ResponseEntity<List<UserRoleDto>> findAllUserRole() {
        return new ResponseEntity<>(userRoleService.findAllUserRole(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUserRole(@RequestBody UserRoleRequest request) {
        userRoleService.addUserRole(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
