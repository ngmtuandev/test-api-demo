package com.bu3.skeleton.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "`booking`")
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

    private boolean isConfirm;

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;

    @OneToMany(mappedBy = "booking")
    private List<BookingDetail> bookingDetails;

    @OneToMany(mappedBy = "booking")
    private List<GuestInfo> guestInfos;
}
