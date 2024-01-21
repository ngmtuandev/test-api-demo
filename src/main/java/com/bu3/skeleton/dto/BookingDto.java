package com.bu3.skeleton.dto;

import java.time.LocalDate;
import java.util.List;

public class BookingDto {
    private Integer quantityRoomOrder;
    private Integer numberOfNights;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String customerName;
    private String customerPhoneNumber;
    private String customerMail;
    private String customerRequirement;
    private String pickUpLocation;
    private PaymentDto payment;
    private List<BookingDetailDto> bookingDetails;
    private List<GuestInfoDto> guestInfos;
}
