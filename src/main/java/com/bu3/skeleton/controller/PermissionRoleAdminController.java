package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.PermissionRoleRequest;
import com.bu3.skeleton.sevice.IPermissionRoleService;
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

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + SystemConstant.API_PERMISSION_ROLE)
@RequiredArgsConstructor
public class PermissionRoleAdminController {

    private final IPermissionRoleService permissionRoleService;

    private final IValidationService validationService;

    @PostMapping
    public ResponseEntity<?> addPermissionRole(
            @Validated @RequestBody PermissionRoleRequest request,
            Errors errors
    ) {
        validationService.handleValidate(errors);
        permissionRoleService.addPermissionRole(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
