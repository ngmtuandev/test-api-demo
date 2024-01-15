package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_location")
@Entity
public class Location extends BaseEntity {

    private String parentId;

    private String languageCode;

    private String locationName;

    private String status;

    private String description;

    @OneToMany(mappedBy = "location")
    private List<LocationHotel> locationHotels;
}
