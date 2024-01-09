package com.bu3.skeleton.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class used to transfer contract information
 *
 * @author hai.huynh
 * @created 09/01/2024
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDto {
    private String appName;
    private String hotline;
    private String facebook;
}
