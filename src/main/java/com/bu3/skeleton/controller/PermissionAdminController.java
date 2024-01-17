package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.PermissionDto;
import com.bu3.skeleton.dto.request.PermissionRequest;
import com.bu3.skeleton.sevice.IPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + SystemConstant.API_PERMISSION)
@RequiredArgsConstructor
public class PermissionAdminController {

    private final IPermissionService permissionService;

    @PostMapping
    public ResponseEntity<?> addPermission(@RequestBody PermissionRequest request) {
        permissionService.addPermission(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PermissionDto>> findAllPermission() {
        return new ResponseEntity<>(permissionService.findAllPermission(), HttpStatus.OK);
    }
}