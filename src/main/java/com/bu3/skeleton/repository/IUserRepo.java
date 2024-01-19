package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepo extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    Page<User> findUsersByIsDeleted(Boolean isDeleted, Pageable pageable);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByEmailAndIsDeleted(String email, Boolean isDeleted);
}
