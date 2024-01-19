package com.bu3.skeleton;

import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.entity.Permission;
import com.bu3.skeleton.entity.PermissionGroup;
import com.bu3.skeleton.entity.PermissionRole;
import com.bu3.skeleton.entity.Role;
import com.bu3.skeleton.entity.User;
import com.bu3.skeleton.entity.UserRole;
import com.bu3.skeleton.repository.IPermissionGroupRepo;
import com.bu3.skeleton.repository.IPermissionRepo;
import com.bu3.skeleton.repository.IPermissionRoleRepo;
import com.bu3.skeleton.repository.IRoleRepo;
import com.bu3.skeleton.repository.IUserRepo;
import com.bu3.skeleton.repository.IUserRoleRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@SpringBootApplication
public class SkeletonServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SkeletonServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            IPermissionGroupRepo permissionGroupRepo,
            IUserRepo userRepo,
            IRoleRepo roleRepo,
            IPermissionRepo permissionRepo,
            IPermissionRoleRepo permissionRoleRepo,
            IUserRoleRepo userRoleRepo,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {
            PermissionGroup permissionGroup = permissionGroupRepo.save(
                    PermissionGroup.builder()
                            .permissionGroupName("ADMIN 1")
                            .isDeleted(SystemConstant.ACTIVE)
                            .description("PermissionGroup description...")
                            .build()
            );

            Permission permission = permissionRepo.save(
                    Permission.builder()
                            .permissionGroup(permissionGroup)
                            .permissionCode("admin:master")
                            .description("Permission description...")
                            .isDeleted(SystemConstant.ACTIVE)
                            .build()
            );

            Role role = roleRepo.save(
                    Role.builder()
                            .roleName("ADMIN")
                            .roleCode("admin:demo")
                            .isDeleted(SystemConstant.ACTIVE)
                            .build()
            );

            PermissionRole permissionRole = permissionRoleRepo.save(
                    PermissionRole.builder()
                            .permission(permission)
                            .role(role)
                            .isDeleted(SystemConstant.ACTIVE)
                            .build()
            );

            User user = userRepo.save(
                    User.builder()
                            .email("hoangduynam20996@gmail.com")
                            .password(passwordEncoder.encode("123123"))
                            .gender("Male")
                            .dateOfBirth(LocalDate.of(1996, 9, 20))
                            .fullName("Duy Nam")
                            .phoneNumber("0968997434")
                            .address("Bau bang")
                            .isDeleted(SystemConstant.ACTIVE)
                            .build()
            );

            userRoleRepo.save(
                    UserRole.builder()
                            .user(user)
                            .role(role)
                            .isDeleted(SystemConstant.ACTIVE)
                            .build()
            );
        };
    }
}
