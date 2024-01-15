package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IUserRoleRepo extends JpaRepository<UserRole, UUID> {
}
