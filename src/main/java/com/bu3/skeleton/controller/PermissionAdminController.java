package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.PermissionRequest;
import com.bu3.skeleton.dto.request.PermissionUpdateRequest;
import com.bu3.skeleton.dto.response.PermissionResponses;
import com.bu3.skeleton.sevice.IPermissionService;
import com.bu3.skeleton.validation.IValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + SystemConstant.API_PERMISSION)
@RequiredArgsConstructor
public class PermissionAdminController {

    private final IPermissionService permissionService;

    private final IValidationService validationService;

    @GetMapping("/{currentPage}/{limitPage}")
    public ResponseEntity<PermissionResponses> findAllPermission(
            @PathVariable("currentPage") Optional<Integer> currentPage,
            @PathVariable("limitPage") Optional<Integer> limitPage
    ) {
        return new ResponseEntity<>(permissionService.findAllPermission(currentPage.orElse(1), limitPage.orElse(10)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPermission(@RequestBody PermissionRequest request) {
        permissionService.addPermission(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updatePermission(
            @Validated @RequestBody PermissionUpdateRequest request,
            Errors errors
    ) {
        validationService.handleValidate(errors);
        permissionService.updatePermission(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePermission(@RequestParam("permissionId") UUID permissionId) {
        permissionService.deletePermission(permissionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
