package com.bu3.skeleton.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * This class used to handle send mail request
 *
 * @author hai.huynh
 * @created 09/01/2024
 */
@Data
public class SendMailRequest {
    @NotNull
    private String toAddress;

}
