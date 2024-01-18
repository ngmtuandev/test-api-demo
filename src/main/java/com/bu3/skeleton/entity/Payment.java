package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`payment`")
@Entity
public class Payment extends BaseEntity {

    private PaymentStatus status;

    private Double amountPaid;

    private LocalDateTime dateOfPayment;
}
