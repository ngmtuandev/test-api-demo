package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_banner")
@Entity
public class Banner extends BaseEntity {
    private String titleBanner;

    private Boolean isDelete;

    private byte[] data;
}
