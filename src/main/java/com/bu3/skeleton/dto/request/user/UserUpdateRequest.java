package com.bu3.skeleton.dto.request.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequest {

    private String email;

    private String password;

    private String gender;

    private LocalDate dateOfBirth;

    private String fullName;

    private String phoneNumber;

    private String address;

}
