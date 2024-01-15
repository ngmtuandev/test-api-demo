package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.HotelStatus;
import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_hotel")
@EntityListeners(AuditingEntityListener.class)
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
