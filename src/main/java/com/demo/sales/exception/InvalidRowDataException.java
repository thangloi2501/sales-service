package com.demo.sales.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

/**
 * @author Loi Nguyen
 *
 */
@Setter
@AllArgsConstructor @Builder
public class InvalidRowDataException extends RuntimeException {

    public InvalidRowDataException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidRowDataException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}