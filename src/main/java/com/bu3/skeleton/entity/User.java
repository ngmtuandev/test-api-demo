package com.bu3.skeleton.entity;

import com.bu3.skeleton.util.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "_user")
@Entity
public class User extends BaseEntity implements UserDetails {

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirt;

    private String gender;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String status;
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        userRoles.stream()
                .map(role -> {
                    return role.getRole().getPermissionRoles().stream()
                            .map(item -> item.getPermission().getPermissionCode());
                });
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
