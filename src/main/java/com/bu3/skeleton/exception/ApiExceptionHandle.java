package com.bu3.skeleton.exception;

import com.bu3.skeleton.util.BaseAmenity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandle {

    private final BaseAmenity baseAmenity;

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(
                e.getCode(),
                e.getMessage(),
                badRequest,
                baseAmenity.currentTimeSeconds()
        );
        return new ResponseEntity<>(exception, badRequest);
    }


}