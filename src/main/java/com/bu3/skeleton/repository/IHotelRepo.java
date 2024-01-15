package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHotelRepo extends JpaRepository<Hotel, UUID> {
}
