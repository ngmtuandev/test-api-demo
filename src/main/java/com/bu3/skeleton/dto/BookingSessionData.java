package com.bu3.skeleton.dto;

import com.bu3.skeleton.entity.Booking;
import com.bu3.skeleton.entity.BookingDetail;
import com.bu3.skeleton.entity.GuestInfo;
import com.bu3.skeleton.entity.Payment;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingSessionData {

    private Booking newBooking;
    private List<BookingDetail> bookingDetails;
    private List<GuestInfo> guestInfos;
    private Payment methodPayment;

}
