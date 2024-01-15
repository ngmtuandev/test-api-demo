package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_booking")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Booking extends BaseEntity {
    private Integer quantityRoomOrder;
    private Integer numberOfNights;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String customerName;
    private String customerPhoneNumber;
    private String customerMail;
    private String customerRequirement;
    private String pickUpLocation;

    @ManyToOne
    @JoinColumn(name = "booking_room_id", referencedColumnName = "id")
    private Payment payment;

    @OneToMany(mappedBy = "booking")
    private List<BookingDetail> bookingDetails;
}
