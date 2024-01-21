package com.bu3.skeleton.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GuestInfoDto {
    private String titles;
    private String name;
    private String phoneNumber;
    private String email;

    private int age;


}
