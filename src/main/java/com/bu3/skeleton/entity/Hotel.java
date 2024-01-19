package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.HotelStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.OneToOne;
import jakarta.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`hotel`")
@Entity
public class Hotel extends BaseEntity {

    private Integer star;

    private BigDecimal price;

    private HotelStatus status;

    @OneToOne(mappedBy = "hotel")
    private HotelContact hotelContact;

    @OneToMany(mappedBy = "hotel")
    private List<HotelImage> hotelImages;

    @OneToMany(mappedBy = "hotel")
    private List<HotelInfo> hotelInfos;

    @OneToMany(mappedBy = "hotel")
    private List<Room> rooms;
}
