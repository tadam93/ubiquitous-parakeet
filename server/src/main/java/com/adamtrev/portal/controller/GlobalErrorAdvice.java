package com.adamtrev.portal.controller;

import com.adamtrev.portal.data.StaleDataException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalErrorAdvice {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(StaleDataException.class)
    public void conflictingResource() {

    }
}
