package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Booking;
import feign.Param;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBookingRepo extends JpaRepository<Booking, UUID> {

    @Query("""
                SELECT b FROM Booking b
                INNER JOIN BookingDetail bd ON b.id = bd.booking.id
                INNER JOIN Room r ON r.id = bd.room.id
                where r.hotel.id = ?1
            """)
    List<Booking> findHotelsByHotelId(UUID hotelId);

    @EntityGraph(attributePaths = {"payment", "bookingDetails.room", "guestInfos"})
    Optional<Booking> findWithDetailsById(UUID id);

    @Query("SELECT b FROM Booking b " +
            "LEFT JOIN FETCH b.bookingDetails bd " +
            "LEFT JOIN bd.room " +
            "LEFT JOIN FETCH b.guestInfos " +
            "WHERE b.id = :bookingId")
    Optional<Booking> findByIdWithDetails(@Param("bookingId") UUID bookingId);

}
