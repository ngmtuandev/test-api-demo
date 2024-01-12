package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.ContactHotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IContactHotelRepo extends JpaRepository<ContactHotel, UUID> {
}
