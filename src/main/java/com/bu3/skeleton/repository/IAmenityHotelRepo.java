package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.AmenityHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAmenityHotelRepo extends JpaRepository<AmenityHotel, UUID> {
}
