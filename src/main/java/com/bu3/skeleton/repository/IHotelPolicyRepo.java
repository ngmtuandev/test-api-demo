package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.HotelPolicy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IHotelPolicyRepo extends JpaRepository<HotelPolicy, UUID> {
}
