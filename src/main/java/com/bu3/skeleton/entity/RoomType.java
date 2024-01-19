package com.bu3.skeleton.entity;

import com.bu3.skeleton.enums.Language;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
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
@Table(name = "`room_type`")
@Entity
public class RoomType extends BaseEntity{

    private String name;

    @Enumerated(EnumType.STRING)
    private Language languageCode;
}
