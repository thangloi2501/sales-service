package com.demo.sales.service.helper;

import com.demo.sales.exception.InvalidRowDataException;
import com.demo.sales.exception.ValidationError;
import com.demo.sales.model.StoreOrder;
import com.demo.sales.utils.AppUtils;
import lombok.experimental.UtilityClass;
import one.util.streamex.StreamEx;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import java.util.Comparator;

@UtilityClass
public final class OrderServiceHelper {
    /**
     * Validate order then return error details (if any)
     */
    public static void validateOrder(StoreOrder order) {
        String errorDetails = StreamEx.of(AppUtils.validate(order))
                                      .sorted(Comparator.comparing(ValidationError::getName))
                                      .joining(". ");

        if (StringUtils.isNotBlank(errorDetails))
            throw new InvalidRowDataException(errorDetails);
    }

    public static String getImportThrowableMessage(Throwable ex) {
        if (ex.getCause() instanceof ConstraintViolationException)
            return ((org.hibernate.exception.ConstraintViolationException) ex.getCause()).getSQLException()
                                                                                         .getMessage();
        else
            return ex.getMessage();
    }
}
