package com.demo.sales.exception;

import one.util.streamex.StreamEx;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * @author Loi Nguyen
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiException apiError) {
        apiError.setStackTrace(new StackTraceElement[0]);
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private String processFieldErrors(List<FieldError> fieldErrors) {
        return StreamEx.of(fieldErrors)
                       .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                       .joining(",");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {

        return buildResponseEntity(new ApiException(HttpStatus.BAD_REQUEST, processFieldErrors(ex.getBindingResult().getFieldErrors()), ex));
    }
}
