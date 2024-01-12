package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_room_image")
@Entity
public class RoomImage extends BaseEntity {
    private String name;
    private byte[] data;
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn( name = "room_id", referencedColumnName = "id")
    private Room room;
}
