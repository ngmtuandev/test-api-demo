package com.bu3.skeleton.exception;

import lombok.Getter;

/**
 * This class used to handle data not found exception
 *
 * @author hai.huynh
 * @created 09/01/2024
 */
@Getter
public class DataNotFoundException extends RuntimeException {
    private final String code;
    private final String message;

    public DataNotFoundException(String code, String messsage) {
        this.code = code;
        this.message = messsage;
    }
}
