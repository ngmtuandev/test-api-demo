package com.bu3.skeleton.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`permission_group`")
@Entity
public class PermissionGroup extends BaseEntity {

    private String permissionGroupName;

    private String description;
    @OneToMany(mappedBy = "permissionGroup")
    private List<Permission> permissions;

}
