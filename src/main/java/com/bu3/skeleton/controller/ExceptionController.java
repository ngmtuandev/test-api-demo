package com.bu3.skeleton.controller;

import com.bu3.skeleton.exception.DataNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    private static final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity handleDataNotFoundException(DataNotFoundException ex) {
        return new ResponseEntity<>(null);
    }
}
