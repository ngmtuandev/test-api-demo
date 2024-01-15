package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.ActivityHistoryType;
import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_activity_history")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class ActivityHistory extends BaseEntity {
    private ActivityHistoryType type;
    private String title;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
