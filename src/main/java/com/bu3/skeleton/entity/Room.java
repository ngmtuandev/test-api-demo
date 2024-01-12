package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_room")
@Entity
public class Room extends BaseEntity {
    private Double acreage;
    private Integer capacity;
    private Integer quantity; //tong so phong
    private BigDecimal price;
    private Integer adultQuantity;
    private Integer childrenQuantity;
    private Integer singleBedQuantity;
    private Integer twinBedQuantity;
    private Integer subBedQuantity;

    @OneToOne
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private RoomInfo roomInfo;

    @OneToMany(mappedBy = "room")
    private List<RoomImage> roomImages;

    @ManyToOne
    @JoinColumn(name = "room_type_id", referencedColumnName = "id")
    private RoomType roomType;
}
