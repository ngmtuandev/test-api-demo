package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_amenity")
@Entity
public class Amenity extends BaseEntity {

    private String parentId;

    private String languageCode;

    private String amenityHotelName;

    private Boolean isDeleted;

    private String description;

    @OneToMany(mappedBy = "amenity")
    private List<AmenityHotel> amenityHotels;
}
