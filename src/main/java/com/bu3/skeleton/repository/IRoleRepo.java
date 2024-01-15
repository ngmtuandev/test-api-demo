package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IRoleRepo extends JpaRepository<Role, UUID> {

    boolean existsByRoleNameNot(String roleName);

    boolean existsByRoleName(String roleName);

    Optional<Role> findRoleByRoleName(String roleName);
}
