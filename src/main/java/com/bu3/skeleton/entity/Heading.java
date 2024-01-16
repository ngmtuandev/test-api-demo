package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "'heading'")
@Entity
public class Heading extends BaseEntity {

    private String parentId;
    private String languageCode;

    private String locationName;
    private String urlHeading;
    private String status;
    private String titleHeading;
    @OneToMany(mappedBy = "heading")
    private List<SubHeading> subHeadings;
}
