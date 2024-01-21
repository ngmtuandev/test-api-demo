package com.bu3.skeleton.dto;

import com.bu3.skeleton.enums.PaymentStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
    @Setter
    @Builder
    public class PaymentDto {
        private PaymentStatus status;
        private Double amountPaid;
        private LocalDateTime dateOfPayment;
    }

