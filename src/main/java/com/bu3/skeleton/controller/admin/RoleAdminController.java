package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.RoleConstant;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.dto.RoleDto;
import com.bu3.skeleton.dto.request.role.RoleAddRequest;
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

import java.util.List;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + RoleConstant.API_ROLE)
@RequiredArgsConstructor
public class RoleAdminController {

    private final IRoleService roleService;

    @PostMapping
    public ResponseEntity<?> addRole(@Valid @RequestBody RoleAddRequest request) {
        roleService.addRole(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> findAllRole() {
        return new ResponseEntity<>(roleService.findAllRole(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateRole(
            @Valid @RequestBody RoleAddRequest request
    ) {
        roleService.updateRole(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
