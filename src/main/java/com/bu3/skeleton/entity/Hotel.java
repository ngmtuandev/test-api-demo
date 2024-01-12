package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_hotel")
@Entity
public class Hotel extends BaseEntity {

    private String parentId;

    private Double rating;

    private BigDecimal priceBase;

    private String status;
}
