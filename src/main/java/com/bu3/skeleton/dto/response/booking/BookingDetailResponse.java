package com.bu3.skeleton.dto.response.booking;

import com.bu3.skeleton.dto.BookingDetailDto;
import com.bu3.skeleton.dto.BookingDto;
import com.bu3.skeleton.dto.GuestInfoDto;
import com.bu3.skeleton.entity.Booking;
import com.bu3.skeleton.entity.BookingDetail;
import com.bu3.skeleton.entity.GuestInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDetailResponse {
    private BookingDto booking;
    private List<BookingDetailDto> bookingDetails;
    private List<GuestInfoDto> guestInfos;
}
