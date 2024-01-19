package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IRoomRepo extends JpaRepository<Room, UUID> {
}
