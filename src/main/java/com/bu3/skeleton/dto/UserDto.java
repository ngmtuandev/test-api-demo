package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class UserDto {

    private String email;

    private String gender;

    private LocalDate dateOfBirth;

    private String fullName;

    private String phoneNumber;

    private String address;

    private String status;

    private String jwtToken;
}