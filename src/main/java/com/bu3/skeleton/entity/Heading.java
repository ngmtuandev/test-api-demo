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
@Table(name = "_heading")
@Entity
public class Heading extends BaseEntity {

    private String parentId;

    private String urlHeading;

    private String status;

    private String languageCode;

    private String titleHeading;

    @OneToMany(mappedBy = "heading")
    private List<SubHeading> subHeadings;
}
