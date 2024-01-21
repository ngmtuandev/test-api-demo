package com.bu3.skeleton.repository;

import com.bu3.skeleton.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPaymentRepo extends JpaRepository<Payment, UUID> {
}
