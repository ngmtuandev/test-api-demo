package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.PermissionRoleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.permissionrole.PermissionRoleRequest;
import com.bu3.skeleton.sevice.IPermissionRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + PermissionRoleConstant.API_PERMISSION_ROLE)
@RequiredArgsConstructor
public class PermissionRoleAdminController {

    private final IPermissionRoleService permissionRoleService;

    @PostMapping
    public ResponseEntity<?> addPermissionRole(
            @Valid @RequestBody PermissionRoleRequest request
    ) {
        permissionRoleService.addPermissionRole(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
