package com.demo.sales.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * @author Loi Nguyen
 *
 */
@Setter
@AllArgsConstructor @Builder
public class ApiException extends RuntimeException {

    @Builder.Default
    @Getter
    private HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    @Getter
    private long timestamp;
    @Getter
    private String message;
    @Getter
    private String debugMessage;

    public ApiException() {
        timestamp = System.currentTimeMillis();
    }

    public ApiException(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiException(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message = message;
    }

    public ApiException(HttpStatus status, Throwable ex) {
        this();
        this.status = status;
        this.message = "Unexpected error";
        this.debugMessage = ex.getLocalizedMessage();
    }

    public ApiException(HttpStatus status, String message, String debugMessage) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = debugMessage;
    }

    public ApiException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.debugMessage = ex.getLocalizedMessage();
    }

}