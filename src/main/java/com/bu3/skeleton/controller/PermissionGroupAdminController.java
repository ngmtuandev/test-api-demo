package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.PermissionGroupRequest;
import com.bu3.skeleton.dto.response.PermissionGroupResponses;
import com.bu3.skeleton.sevice.IPermissionGroupService;
import com.bu3.skeleton.validation.IValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @GetMapping("/{currentPage}/{limitPage}")
    public ResponseEntity<PermissionGroupResponses> findAllPermissionGroup(
            @PathVariable(value = "currentPage", required = false) Optional<Integer> currentPage,
            @PathVariable(value = "limitPage", required = false) Optional<Integer> limitPage
    ) {
        return new ResponseEntity<>(permissionGroupService.findAllPermissionGroup(currentPage.orElse(1), limitPage.orElse(8)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updatePermissionGroup(
            @Validated @RequestBody PermissionGroupRequest request,
            Errors errors
    ) {
        validationService.handleValidate(errors);
        permissionGroupService.updatePermissionGroup(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{permission-group-name}")
    public ResponseEntity<?> deletePermissionGroup(@PathVariable("permission-group-name") String permissionGroupName) {
        permissionGroupService.deletePermissionGroup(permissionGroupName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
