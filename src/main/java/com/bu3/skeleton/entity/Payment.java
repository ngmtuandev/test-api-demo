package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.PaymentStatus;
import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "'payment'")
@Entity
public class Payment extends BaseEntity {
    private PaymentStatus status;
    private Double amountPaid;
    private LocalDateTime dateOfPayment;
}
