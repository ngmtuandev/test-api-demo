package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepo extends JpaRepository<User, UUID> {

    boolean existsByEmail(String email);

    @Query("""
            SELECT o FROM User  o
            """)
    List<User> findAllUser(Pageable pageable);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByEmailAndIsDeleted(String email, Boolean isDeleted);
}
