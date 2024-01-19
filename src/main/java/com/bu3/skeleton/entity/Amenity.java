package com.bu3.skeleton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`amenity`")
@Entity
public class Amenity extends BaseEntity {

    private String parentId;

    private String languageCode;

    private String amenityHotelName;

    private String description;

    @OneToMany(mappedBy = "amenity")
    private List<AmenityHotel> amenityHotels;
}
