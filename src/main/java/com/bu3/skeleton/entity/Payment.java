package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.PaymentStatus;
import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_payment")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Payment extends BaseEntity {
    private PaymentStatus status;
    private Double amountPaid;
    private LocalDateTime dateOfPayment;
}
