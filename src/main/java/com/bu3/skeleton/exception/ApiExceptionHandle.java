package com.bu3.skeleton.exception;

import com.bu3.skeleton.configuration.Translator;
import com.bu3.skeleton.constant.SystemConstant;
import com.bu3.skeleton.constant.TransitionCode;
import com.bu3.skeleton.util.BaseAmenityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandle {

    private final BaseAmenityUtil baseAmenity;

    private String extractErrorCode(String fullErrorMessage) {
        int startBracketIndex = fullErrorMessage.indexOf("Detail:");
        int endBracketIndex = fullErrorMessage.indexOf(']');

        if (startBracketIndex != -1 && endBracketIndex != -1 && startBracketIndex < endBracketIndex) {
            return fullErrorMessage.substring(startBracketIndex + 1, endBracketIndex).trim();
        }

        return fullErrorMessage;
    }

    @ExceptionHandler(value = ApiRequestException.class)
    public ResponseEntity<Object> handleException(ApiRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException exception = new ApiException(
                e.getCode(),
                SystemConstant.STATUS_CODE_BAD_REQUEST,
                e.getMessage(),
                baseAmenity.currentTimeSeconds()
        );
        return new ResponseEntity<>(exception, badRequest);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleException(DataIntegrityViolationException e) {

        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException exception = new ApiException(
                Translator.toLocale(TransitionCode.ERROR_SERVER),
                SystemConstant.STATUS_CODE_INTERNAL,
                extractErrorCode(e.getMessage()),
                baseAmenity.currentTimeSeconds()
        );
        return new ResponseEntity<>(exception, internalServerError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        HttpStatus internalServerError = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException exception = new ApiException(
                Translator.toLocale(TransitionCode.ERROR_SERVER),
                SystemConstant.STATUS_CODE_INTERNAL,
                e.getMessage(),
                baseAmenity.currentTimeSeconds()
        );
        return new ResponseEntity<>(exception, internalServerError);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        List<String> listError = new ArrayList<>();

        if (e.getBindingResult().hasErrors()) {
            listError = e.getBindingResult().getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
        }
        ApiException exception = new ApiException(
                Translator.toLocale(TransitionCode.ERROR_VALIDATION_FIELD),
                SystemConstant.STATUS_CODE_BAD_REQUEST,
                listError.toString(),
                baseAmenity.currentTimeSeconds()
        );
        return new ResponseEntity<>(exception, badRequest);
    }

}