package com.bu3.skeleton.controller.admin;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.UserRoleConstant;
import com.bu3.skeleton.dto.request.userrole.UserRoleAddRequest;
import com.bu3.skeleton.dto.request.userrole.UserRoleUpdateRequest;
import com.bu3.skeleton.dto.response.userrole.UserRoleResponse;
import com.bu3.skeleton.dto.response.userrole.UserRoleResponses;
import com.bu3.skeleton.sevice.IUserRoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(SystemConstant.API_ADMIN + SystemConstant.VERSION_1 + UserRoleConstant.API_USER_ROLE)
@RequiredArgsConstructor
public class UserRoleAdminController {

    private final IUserRoleService userRoleService;

    /*
     * Get all user no active and active
     * */
    @GetMapping
    public ResponseEntity<UserRoleResponses> findAllUserRole(
            @RequestParam(value = SystemConstant.CURRENT_PAGE, required = false) Optional<Integer> currentPage,
            @RequestParam(value = SystemConstant.LIMIT_PAGE, required = false) Optional<Integer> limitPage
    ) {
        return new ResponseEntity<>(userRoleService.findAllUserRole(currentPage.orElse(1), limitPage.orElse(8)), HttpStatus.OK);
    }

    /*
     *
     * */
    @PostMapping
    public ResponseEntity<UserRoleResponse> addUserRole(@Valid @RequestBody UserRoleAddRequest request) {
        return new ResponseEntity<>(userRoleService.addUserRole(request), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserRoleResponse> updateUserRole(@Valid @RequestBody UserRoleUpdateRequest request) {
        return new ResponseEntity<>(userRoleService.updateUserRole(request), HttpStatus.OK);
    }
}
