package com.bu3.skeleton.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class used to transfer contract information
 *
 * @author hai.huynh
 * @created 09/01/2024
 */
@Getter
@Setter
@NoArgsConstructor
public class ContactInfoDto {
    private String appName;
    private String hotline;
    private String facebook;
}
