package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPermissionRepo extends JpaRepository<Permission, UUID> {

    Optional<Permission> findPermissionByPermissionCode(String permissionCode);
}
