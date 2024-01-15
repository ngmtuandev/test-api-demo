package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.Language;
import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "_hotel_info")
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
