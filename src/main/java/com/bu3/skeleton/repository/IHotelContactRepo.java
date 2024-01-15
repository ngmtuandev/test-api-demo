package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.HotelContact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHotelContactRepo extends JpaRepository<HotelContact, UUID> {
}
