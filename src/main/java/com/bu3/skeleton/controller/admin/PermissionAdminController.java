package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.PermissionConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.permission.PermissionRequest;
import com.bu3.skeleton.dto.request.permission.PermissionUpdateRequest;
import com.bu3.skeleton.dto.response.permission.PermissionResponses;
import com.bu3.skeleton.sevice.IPermissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + PermissionConstant.API_PERMISSION)
@RequiredArgsConstructor
public class PermissionAdminController {

    private final IPermissionService permissionService;

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
            @Valid @RequestBody PermissionUpdateRequest request
    ) {
        permissionService.updatePermission(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deletePermission(@RequestParam("permissionId") UUID permissionId) {
        permissionService.deletePermission(permissionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
