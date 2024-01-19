package com.bu3.skeleton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private Payment payment;
    @OneToMany(mappedBy = "booking")
    private List<BookingDetail> bookingDetails;
}
