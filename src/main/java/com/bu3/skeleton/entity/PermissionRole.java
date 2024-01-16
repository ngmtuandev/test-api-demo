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
@Table(name = "'permission_role'")
@Entity
public class PermissionRole extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
