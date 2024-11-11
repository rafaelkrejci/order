package br.com.krejci.orders.configuration;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleConflict() {
        // Nothing to do
    }
}
