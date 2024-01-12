package com.bu3.skeleton.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_hotel_image")
@EntityListeners(AuditingEntityListener.class)
@Entity
public class HotelImage {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
    @Column
    private String imageName;
    @Column
    private String type;
    @Column(length = 5000000)
    private byte[] picByte;
    @Column
    @CreatedDate
    private LocalDateTime uploadDate;

    private Boolean isDeleted;
}
