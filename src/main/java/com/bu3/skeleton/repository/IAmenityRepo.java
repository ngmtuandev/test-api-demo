package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAmenityRepo extends JpaRepository<Amenity, UUID> {
}
