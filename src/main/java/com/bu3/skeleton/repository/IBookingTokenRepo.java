package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.BookingToken;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookingTokenRepo extends JpaRepository<BookingToken, UUID> {
}
