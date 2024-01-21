package com.bu3.skeleton.dto;

import com.bu3.skeleton.entity.Hotel;
import com.bu3.skeleton.entity.RoomInfo;
import com.bu3.skeleton.enums.RoomStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class RoomDto {
    private Integer quantityRoom;

    private BigDecimal price;

    private Double acreage;

    private Integer capacity;

    private Integer adultQuantity;

    private Integer childrenQuantity;

    private Integer extraBedQuantity;

    private Integer singleBedQuantity;

    private Integer twinBedQuantity;

    private RoomStatus status;

    private Hotel hotel;

    private List<RoomInfoDto> roomInfo;

    private List<RoomImageDto> roomImages;
}
