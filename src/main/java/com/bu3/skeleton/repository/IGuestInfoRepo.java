package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.GuestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IGuestInfoRepo extends JpaRepository<GuestInfo, UUID> {

    List<GuestInfo> findByBooking_Id(UUID bookingId);


}
