package com.bu3.skeleton.exception;

import lombok.Getter;

@Getter
public class ApiRequestException extends RuntimeException {

    private final String code;
    private final String message;

    public ApiRequestException(String code, String messsage) {
        this.code = code;
        this.message = messsage;
    }
}
