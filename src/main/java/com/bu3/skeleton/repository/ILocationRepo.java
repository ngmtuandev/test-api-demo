package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ILocationRepo extends JpaRepository<Location, UUID> {
}
