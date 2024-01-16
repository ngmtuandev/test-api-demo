package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.Language;
import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "'hotel_info'")
@Entity
public class HotelInfo extends BaseEntity {

    private String name;

    private String address;

    private String city;

    private String currency;

    private String description;

    private Language languageCode;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "hotelInfo")
    private List<HotelPolicy> hotelPolicies;

    @OneToMany(mappedBy = "hotelInfo")
    private List<ServiceForHotel> serviceForHotels;
}
