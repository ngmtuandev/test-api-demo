package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "'amenity_hotel'")
@Entity
public class AmenityHotel extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @ManyToOne
    @JoinColumn(name = "amenity_id")
    private Amenity amenity;

    private String parentId;

    private String languageCode;

    private Boolean isDeleted;

    private String description;
}
