package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.RoomImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IRoomImageRepo extends JpaRepository<RoomImage, UUID> {
    List<RoomImage> findAllByRoomId(UUID roomId);

}
