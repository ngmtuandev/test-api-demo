package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPermissionGroupRepo extends JpaRepository<PermissionGroup, UUID> {

    boolean existsByPermissionGroupName(String permissionGroupName);

    boolean existsByPermissionGroupNameNot(String permissionGroupName);

    Optional<PermissionGroup> findPermissionGroupByPermissionGroupName(String permissionGroupName);
}
