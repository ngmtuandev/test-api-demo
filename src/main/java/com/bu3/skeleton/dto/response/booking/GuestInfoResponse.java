package com.bu3.skeleton.dto.response.booking;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class GuestInfoResponse {
    private UUID guestId;
    private String guestName;
}
