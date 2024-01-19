package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.RoomDto;
import com.bu3.skeleton.dto.request.room.RoomRequest;
import com.bu3.skeleton.dto.response.room.RoomResponse;

import java.util.UUID;

public interface IRoomService {
    void addRoom(RoomRequest roomRequest);

    RoomResponse findRooms(Integer currentPage, Integer limitPage);

    RoomDto getRoomByUUID(UUID uuid);

    RoomResponse updateRoom(RoomRequest roomRequest, UUID idRoom);

    RoomResponse deleteRoom(UUID uuid);
}
