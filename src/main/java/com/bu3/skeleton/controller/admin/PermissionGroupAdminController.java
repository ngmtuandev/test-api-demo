package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.PermissionGroupConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.permissiongroup.PermissionGroupRequest;
import com.bu3.skeleton.dto.response.permissiongroup.PermissionGroupResponse;
import com.bu3.skeleton.dto.response.permissiongroup.PermissionGroupResponses;
import com.bu3.skeleton.sevice.IPermissionGroupService;
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
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + PermissionGroupConstant.API_PERMISSION_GROUP)
@RequiredArgsConstructor
public class PermissionGroupAdminController {

    private final IPermissionGroupService permissionGroupService;

    @PostMapping
    public ResponseEntity<PermissionGroupResponse> addPermissionGroup(
            @Valid @RequestBody PermissionGroupRequest request
    ) {
        return new ResponseEntity<>(permissionGroupService.addPermissionGroup(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PermissionGroupResponses> findAllPermissionGroup(
            @RequestParam(value = SystemConstant.CURRENT_PAGE, required = false) Optional<Integer> currentPage,
            @RequestParam(value = SystemConstant.LIMIT_PAGE, required = false) Optional<Integer> limitPage
    ) {
        return new ResponseEntity<>(permissionGroupService.findAllPermissionGroup(currentPage.orElse(1), limitPage.orElse(8)), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<PermissionGroupResponse> updatePermissionGroup(
            @Valid @RequestBody PermissionGroupRequest request
    ) {
        return new ResponseEntity<>(permissionGroupService.updatePermissionGroup(request), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<PermissionGroupResponse> deletePermissionGroup(
            @RequestParam("permissionGroupName") String permissionGroupName
    ) {
        return new ResponseEntity<>(permissionGroupService.deletePermissionGroup(permissionGroupName), HttpStatus.OK);
    }
}
