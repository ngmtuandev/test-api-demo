package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.RoomInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IRoomInfoRepo extends JpaRepository<RoomInfo, UUID> {
    List<RoomInfo> findAllByRoomId(UUID roomId);
}
