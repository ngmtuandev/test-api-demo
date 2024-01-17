package com.bu3.skeleton.exception;

import com.bu3.skeleton.util.TimeUnitResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.TimeUnit;

@ControllerAdvice
public class ApiExceptionHandle {

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(
                e.getCode(),
                e.getMessage(),
                badRequest,
                TimeUnitResponse.currentTimeSeconds()
        );
        return new ResponseEntity<>(exception, badRequest);
    }


}