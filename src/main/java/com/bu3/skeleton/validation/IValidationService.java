package com.bu3.skeleton.validation;

import org.springframework.validation.Errors;

public interface IValidationService {

    void handleValidate(Errors errors);
}
