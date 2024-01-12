package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_room_utility_item")
@Entity
public class RoomUtilityItem extends BaseEntity{
    @ManyToOne
    @JoinColumn( name = "room_info_id", referencedColumnName = "id")
    private RoomInfo roomInfo;

    @ManyToOne
    @JoinColumn( name = "room_utility_id", referencedColumnName = "id")
    private RoomUtility roomUtility;
}
