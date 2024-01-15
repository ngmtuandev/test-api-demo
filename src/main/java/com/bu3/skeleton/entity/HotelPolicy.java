package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.Language;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_hotel_policy")
@Entity
public class HotelPolicy {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String mainData;

    private String cancelType;

    private String cancelData;

    private String description;

    private Language languageCode;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    private String parentId;

    private String policy;

    private String typeCancelPolicy;

    private String cancelPolicy;

    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "hotel_info_id", referencedColumnName = "id")
    private HotelInfo hotelInfo;
}
