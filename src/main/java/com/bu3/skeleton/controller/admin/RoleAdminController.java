package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.RoleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.request.role.RoleAddRequest;
import com.bu3.skeleton.dto.response.role.RoleResponse;
import com.bu3.skeleton.dto.response.role.RoleResponses;
import com.bu3.skeleton.sevice.IRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + RoleConstant.API_ROLE)
@RequiredArgsConstructor
public class RoleAdminController {

    private final IRoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponse> addRole(@Valid @RequestBody RoleAddRequest request) {
        return new ResponseEntity<>(roleService.addRole(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<RoleResponses> findAllRole() {
        return new ResponseEntity<>(roleService.findAllRole(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RoleResponse> updateRole(
            @Valid @RequestBody RoleAddRequest request
    ) {
        return new ResponseEntity<>(roleService.updateRole(request), HttpStatus.OK);
    }
}
