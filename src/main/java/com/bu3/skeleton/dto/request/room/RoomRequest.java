package com.bu3.skeleton.dto.request.room;

import com.bu3.skeleton.dto.RoomImageDto;
import com.bu3.skeleton.enums.RoomStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class RoomRequest {

    @Min(value = 1, message = "Min value = 1")
    @Max(value = 100, message = "Max value = 100")
    private Integer quantityRoom;

    @Min(value = 10000, message = "Min value = 10000")
    private BigDecimal price;

    @Min(value = 30, message = "Min value = 30")
    @Max(value = 1000, message = "Max value = 1000")
    private Double acreage;

    @Min(value = 1, message = "Min value = 1")
    @Max(value = 10, message = "Max value = 10")
    private Integer capacity;

    @Min(value = 1, message = "Min value = 1")
    @Max(value = 30, message = "Max value = 30")
    private Integer adultQuantity;

    @Min(value = 1, message = "Min value = 1")
    @Max(value = 30, message = "Max value = 30")
    private Integer childrenQuantity;

    @Min(value = 1, message = "Min value = 1")
    @Max(value = 20, message = "Max value = 20")
    private Integer extraBedQuantity;

    @Min(value = 1, message = "Min value = 1")
    @Max(value = 20, message = "Max value = 20")
    private Integer singleBedQuantity;

    @Min(value = 1, message = "Min value = 1")
    @Max(value = 20, message = "Max value = 20")
    private Integer twinBedQuantity;

    @NotNull(message = "Not empty")
    private RoomStatus status;

    private UUID hotelId;

    @Valid
    private List<RoomInfoRequest> roomInfo;

    @Valid
    private List<RoomImageDto> roomImages;
}
