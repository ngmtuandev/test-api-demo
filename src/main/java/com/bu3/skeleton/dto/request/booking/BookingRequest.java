package com.bu3.skeleton.dto.request.booking;

import com.bu3.skeleton.dto.GuestInfoDto;
import com.bu3.skeleton.entity.BookingDetail;
import com.bu3.skeleton.entity.Payment;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private Integer quantityRoomOrder;

    private Integer numberOfNights;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String customerName;

    private String customerPhoneNumber;

    private String customerMail;

    private String customerRequirement;

    private String pickUpLocation;

    private Payment payment;

    private List<GuestInfoDto> guestInfos;

//    private List<BookingDetail> bookingDetails;
    private List<UUID> idRooms;
}
