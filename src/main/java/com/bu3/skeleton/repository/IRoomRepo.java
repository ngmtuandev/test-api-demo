package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Room;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IRoomRepo extends JpaRepository<Room, UUID> {
//    @Query("""
//    SELECT room FROM Room room
//    JOIN room.bookingDetails bookingDetail
//    WHERE bookingDetail.booking.id = :bookingId
//""")
//    List<Room> findRoomsByBookingId(UUID bookingId);

    @Query("SELECT DISTINCT bd.room FROM BookingDetail bd WHERE bd.booking.id = :bookingId AND bd.isDeleted = false")
    List<Room> findRoomsByBookingId(@Param("bookingId") UUID bookingId);

}
