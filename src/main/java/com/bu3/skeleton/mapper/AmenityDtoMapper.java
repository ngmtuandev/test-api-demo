package com.bu3.skeleton.mapper;

import com.bu3.skeleton.dto.AmenityDto;
import com.bu3.skeleton.entity.Amenity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class AmenityDtoMapper implements Function<Amenity, AmenityDto> {

    @Override
    public AmenityDto apply(Amenity amenity) {
        return AmenityDto.builder()
                .amenityId(amenity.getId())
                .parentId(amenity.getParentId())
                .amenityHotelName(amenity.getAmenityHotelName())
                .isDeleted(amenity.getIsDeleted())
                .description(amenity.getDescription())
                .build();
    }
}
