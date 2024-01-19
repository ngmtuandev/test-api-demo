package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.PermissionConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.permission.PermissionRequest;
import com.bu3.skeleton.dto.request.permission.PermissionUpdateRequest;
import com.bu3.skeleton.dto.response.permission.PermissionResponse;
import com.bu3.skeleton.dto.response.permission.PermissionResponses;
import com.bu3.skeleton.sevice.IPermissionService;
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
import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + PermissionConstant.API_PERMISSION)
@RequiredArgsConstructor
public class PermissionAdminController {

    private final IPermissionService permissionService;

    @GetMapping()
    public ResponseEntity<PermissionResponses> findAllPermission(
            @RequestParam(value = SystemConstant.CURRENT_PAGE, required = false) Optional<Integer> currentPage,
            @RequestParam(value = SystemConstant.LIMIT_PAGE, required = false) Optional<Integer> limitPage
    ) {
        return new ResponseEntity<>(permissionService.findAllPermission(currentPage.orElse(1), limitPage.orElse(10)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PermissionResponse> addPermission(@RequestBody PermissionRequest request) {
        return new ResponseEntity<>(permissionService.addPermission(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<PermissionResponse> updatePermission(
            @Valid @RequestBody PermissionUpdateRequest request
    ) {
        return new ResponseEntity<>(permissionService.updatePermission(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<PermissionResponse> deletePermission(@RequestParam("permissionId") UUID permissionId) {
        return new ResponseEntity<>(permissionService.deletePermission(permissionId), HttpStatus.OK);
    }
}
