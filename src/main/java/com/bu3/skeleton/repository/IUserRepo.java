package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepo extends JpaRepository<User, UUID> {

    Optional<User> findUserByEmailAndStatus(String email, String status);
}
