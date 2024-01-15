package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.HotelServiceCatalogType;
import com.bu3.skeleton.enums.Language;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_hotel_service_catalog")
@Entity
public class HotelServiceCatalog {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    private String name;

    @Enumerated(EnumType.STRING)
    private HotelServiceCatalogType type;
    private Boolean isDeleted;
    private Language languageCode;
}