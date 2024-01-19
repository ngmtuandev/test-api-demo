package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IUserRoleRepo extends JpaRepository<UserRole, UUID> {

    @Query("""
            SELECT
                ur
            FROM
                UserRole ur
            INNER JOIN
                Role r ON r.id = ur.role.id
            INNER JOIN
                User u ON u.id = ur.user.id
            WHERE
                u.email = ?1 AND r.roleName = ?2
             """)
    Optional<UserRole> findUserRoleByRoleNameAndEmail(String roleName, String email);
}
