package com.bu3.skeleton.sevice;

import com.bu3.skeleton.dto.request.booking.BookingRequest;
import com.bu3.skeleton.dto.response.booking.BookingResponse;

import java.util.UUID;

public interface IBookingService {
    String createBooking(BookingRequest bookingRequest);

    BookingResponse getBooking(UUID booking_id);

    String confirmBooking(String tokenID);

}
