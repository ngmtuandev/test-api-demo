package com.bu3.skeleton.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

public record ApiException(
        String code,
        String status,
        HttpStatus message,
        long responseTime
) {
}
