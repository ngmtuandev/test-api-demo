package com.bu3.skeleton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`description`")
@Entity
public class Description extends BaseEntity {

    private String parentId;

    private String languageCode;

    private String titleDescription;

    private String contentDescription;
}
