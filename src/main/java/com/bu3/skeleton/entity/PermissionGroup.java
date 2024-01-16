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
@Table(name = "'permission_group'")
@Entity
public class PermissionGroup extends BaseEntity {

    private String permissionGroupName;
    private Boolean isDeleted;
    private String description;

    @OneToMany(mappedBy = "permissionGroup")
    private List<Permission> permissions;

}
