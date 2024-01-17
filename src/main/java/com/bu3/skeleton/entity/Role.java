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
@Table(name = "'role'")
@Entity
public class Role extends BaseEntity {

    private String roleName;

    private String roleCode;

    private String status;

    @OneToMany(mappedBy = "role")
    private List<UserRole> userRoles;
    @OneToMany(mappedBy = "role")
    private List<PermissionRole> permissionRoles;
}
