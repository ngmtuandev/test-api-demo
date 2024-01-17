package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.ActivityHistoryType;
import com.bu3.skeleton.util.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "'activity_history'")
@Entity
public class ActivityHistory extends BaseEntity {
    private ActivityHistoryType type;
    private String title;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
