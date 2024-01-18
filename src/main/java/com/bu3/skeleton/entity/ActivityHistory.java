package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.ActivityHistoryType;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`activity_history`")
@Entity
public class ActivityHistory extends BaseEntity {

    private ActivityHistoryType type;

    private String title;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;
}
