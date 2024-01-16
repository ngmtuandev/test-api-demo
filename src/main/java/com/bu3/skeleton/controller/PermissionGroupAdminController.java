package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.PermissionGroupDto;
import com.bu3.skeleton.dto.request.PermissionGroupRequest;
import com.bu3.skeleton.sevice.IPermissionGroupService;
import com.bu3.skeleton.validation.IValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + SystemConstant.API_PERMISSION_GROUP)
@RequiredArgsConstructor
public class PermissionGroupAdminController {

    private final IPermissionGroupService permissionGroupService;

    private final IValidationService validationService;

    @PostMapping
    public ResponseEntity<?> addPermissionGroup(
            @Validated @RequestBody PermissionGroupRequest request,
            Errors errors
    ) {
        validationService.handleValidate(errors);
        permissionGroupService.addPermissionGroup(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PermissionGroupDto>> findAllPermissionGroup() {
        return new ResponseEntity<>(permissionGroupService.findAllPermissionGroup(), HttpStatus.OK);
    }
}
