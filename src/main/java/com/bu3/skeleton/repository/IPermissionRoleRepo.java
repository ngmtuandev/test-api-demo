package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPermissionRoleRepo extends JpaRepository<PermissionRole, UUID> {
}
