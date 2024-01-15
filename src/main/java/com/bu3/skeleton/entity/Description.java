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
@Table(name = "_description")
@Entity
public class Description extends BaseEntity {

    private String parentId;

    private String languageCode;

    private String status;

    private String titleDescription;

    private String contentDescription;
}
