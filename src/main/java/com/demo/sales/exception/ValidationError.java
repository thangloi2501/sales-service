package com.demo.sales.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.demo.sales.utils.AppUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;

/**
 * @author Loi Nguyen
 *
 */
@Getter
@AllArgsConstructor @Builder
@ToString
@JsonInclude(Include.NON_EMPTY)
public class ValidationError {
    private String name;

    private String message;

    /**
     * Convert ConstraintViolation to ValidationError
     *
     * @param constraintViolation  an instance of ConstraintViolation
     * @return  an instance of ValidationError representing the ConstraintViolation
     */
    public static ValidationError from(ConstraintViolation<?> constraintViolation) {
        String fieldName = constraintViolation.getPropertyPath().toString();

        String fullFieldName = (fieldName.contains(".")) ? StringUtils.substringAfter(constraintViolation.getPropertyPath().toString(), ".") : fieldName;

        String localizedMessage = constraintViolation.getMessage();
        if (localizedMessage.matches("\\{.*\\}"))
            localizedMessage = AppUtils.getMessage(StringUtils.substringBetween(localizedMessage, "{", "}"));

        return ValidationError.builder()
                              .name(fullFieldName)
                              .message(localizedMessage)
                              .build();
    }
}
