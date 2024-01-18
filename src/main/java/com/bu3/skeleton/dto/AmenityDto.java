package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AmenityDto {

    private UUID amenityId;

    private String parentId;

    private String amenityHotelName;

    private String description;

    private Boolean isDeleted;
}
