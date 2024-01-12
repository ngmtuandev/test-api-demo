package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_hotel_information")
@Entity
public class HotelInformation extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String hotelName;

    private String languageCode;

    private String address;

    private String city;

    private String currency;

    private String description;

    private String status;
}
