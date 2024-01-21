package com.bu3.skeleton.dto.response.booking;

import com.bu3.skeleton.dto.GuestInfoDto;
import com.bu3.skeleton.dto.RoomDto;
import com.bu3.skeleton.entity.Booking;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingResponseData {
    private List<RoomDto> rooms;
    private List<GuestInfoDto> guestInfos;
}
