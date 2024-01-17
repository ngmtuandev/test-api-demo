package com.bu3.skeleton.controller;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.RoleRequest;
import com.bu3.skeleton.sevice.IRoleService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + SystemConstant.API_ROLE)
@RequiredArgsConstructor
public class RoleAdminController {

    private final IRoleService roleService;

    private final IValidationService validationService;

    @PostMapping
    public ResponseEntity<?> addRole(@Validated @RequestBody RoleRequest request, Errors errors) {
        validationService.handleValidate(errors);
        roleService.addRole(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAllRole() {
        return new ResponseEntity<>(roleService.findAllRole(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateRole(
            @Validated @RequestBody RoleRequest request,
            Errors errors
    ) {
        validationService.handleValidate(errors);
        roleService.updateRole(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}