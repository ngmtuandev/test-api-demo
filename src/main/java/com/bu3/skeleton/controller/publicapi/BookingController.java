package com.bu3.skeleton.controller.publicapi;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.UserConstant;
import com.bu3.skeleton.dto.request.booking.BookingRequest;
import com.bu3.skeleton.dto.request.user.UserLoginRequest;
import com.bu3.skeleton.dto.response.booking.BookingResponse;
import com.bu3.skeleton.dto.response.user.UserResponse;
import com.bu3.skeleton.sevice.IBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(SystemConstant.API_PUBLIC + SystemConstant.VERSION_1 + UserConstant.BOOKING)
@RequiredArgsConstructor
public class BookingController {
    private final IBookingService bookingService;

    @PostMapping()
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        BookingResponse result = bookingService.createBooking(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooking(@PathVariable UUID id) {
        BookingResponse result = bookingService.getBooking(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
