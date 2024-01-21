package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.BookingDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface IBookingDetailRepo extends JpaRepository<BookingDetail, UUID> {
    List<BookingDetail> findByBooking_Id(UUID bookingId);

}
