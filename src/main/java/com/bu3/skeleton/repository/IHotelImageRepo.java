package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.HotelImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHotelImageRepo extends JpaRepository<HotelImage, UUID> {
}
