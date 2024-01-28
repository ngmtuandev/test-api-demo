package com.bu3.skeleton.controller.publicapi;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.UserConstant;
import com.bu3.skeleton.dto.request.booking.BookingRequest;
import com.bu3.skeleton.dto.request.user.UserLoginRequest;
import com.bu3.skeleton.dto.response.booking.BookingResponse;
import com.bu3.skeleton.dto.response.user.UserResponse;
import com.bu3.skeleton.entity.BookingToken;
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
    public String createBooking(@RequestBody BookingRequest request) {
        String result = bookingService.createBooking(request);
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooking(@PathVariable UUID id) {
        BookingResponse result = bookingService.getBooking(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping("/confirm/{id}")
    public ResponseEntity<?> confirmBooking(@PathVariable String id) {
        String result = bookingService.confirmBooking(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
