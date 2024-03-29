package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.RoomStatus;
import jakarta.persistence.*;
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
@Table(name = "`room`")
@Entity
public class Room extends BaseEntity {

    private Integer quantityRoom;

    private BigDecimal price;

    private Double acreage;

    private Integer capacity;

    private Integer adultQuantity;

    private Integer childrenQuantity;

    private Integer extraBedQuantity;

    private Integer singleBedQuantity;

    private Integer twinBedQuantity;

    private RoomStatus status;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<RoomInfo> roomInfos;

    @OneToMany(mappedBy = "room")
    private List<RoomImage> roomImages;

}
