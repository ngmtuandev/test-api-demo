package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "'sub_heading'")
@Entity
public class SubHeading extends BaseEntity {

    private String status;
    private String urlSubHeading;
    private String parentId;
    private String languageCode;
    private String titleSubHeading;
    private String city;
    private String currency;
    private String description;
    @ManyToOne
    @JoinColumn(name = "heading_id")
    private Heading heading;
}
