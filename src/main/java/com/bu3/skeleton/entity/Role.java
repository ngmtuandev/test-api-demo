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
@Table(name = "`role`")
@Entity
public class Role extends BaseEntity {

    private String roleName;

    private String roleCode;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
    @OneToMany(mappedBy = "role")
    private List<PermissionRole> permissionRoles;
}
