package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.Language;
import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_room_info")
@Entity
public class RoomInfo extends BaseEntity {
    private String name;
    private String description;
    private String status;
    private Language language;

    @OneToOne(mappedBy = "roomInfo")
    private Room room;

    @OneToMany(mappedBy = "roomInfo")
    private List<RoomUtilityItem> roomUtilityItems;

}
