package com.bu3.skeleton.dto;

import com.bu3.skeleton.entity.Booking;
import com.bu3.skeleton.entity.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BookingDetailDto {
    private RoomDto room;
    private BookingDto booking;
}
