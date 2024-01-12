package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_hotel")
@Entity
public class Hotel extends BaseEntity {

    private Double rating;

    private BigDecimal priceBase;

    private String status;

    @OneToMany(mappedBy = "hotel")
    private List<ContactHotel> contactHotels;

    @OneToMany(mappedBy = "hotel")
    private List<HotelImage> hotelImages;

    @OneToMany(mappedBy = "hotel")
    private List<HotelPolicy> hotelPolicies;

    @OneToMany(mappedBy = "hotel")
    private List<AmenityHotel> amenityHotels;

    @OneToMany(mappedBy = "hotel")
    private List<LocationHotel> locationHotels;

    @OneToMany(mappedBy = "hotel")
    private List<HotelInformation> hotelInformations;
}
