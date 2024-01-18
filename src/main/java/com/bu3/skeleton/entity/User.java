package com.bu3.skeleton.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`user`")
@Entity
public class User extends BaseEntity implements UserDetails {
    @Column(unique = true)
    private String email;

    private String password;

    private LocalDate dateOfBirth;

    private String gender;

    private String fullName;

    private String phoneNumber;

    private String address;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRole> userRoles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles.stream()
                .flatMap(userRole -> {
                    Set<GrantedAuthority> roleAuthorities = userRole.getRole().getPermissionRoles().stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getPermission().getPermissionCode()))
                            .collect(Collectors.toSet());

                    // (con prefisso "ROLE_")
                    roleAuthorities.add(new SimpleGrantedAuthority("ROLE_" + userRole.getRole().getRoleName()));
                    return roleAuthorities.stream();
                })
                .collect(Collectors.toSet());
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
