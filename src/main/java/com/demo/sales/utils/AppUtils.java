package com.demo.sales.utils;

import com.demo.sales.exception.ValidationError;
import one.util.streamex.StreamEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.validation.Validator;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author Loi Nguyen
 *
 */
@Component
public class AppUtils {
    private static MessageSource messageSource;
    private static Validator validator;
    private static DateTimeFormatter localDateFormatter;
    private static String appVersion;

    @Autowired
    private AppUtils(MessageSource messageSource,
                     Validator validator,
                     @Value("${application.version:1.x}") String appVersion,
                     @Value("${format.date:dd.MM.yy}") String datePattern) {
        AppUtils.messageSource      = messageSource;
        AppUtils.validator          = validator;
        AppUtils.appVersion         = appVersion;
        AppUtils.localDateFormatter = DateTimeFormatter.ofPattern(datePattern);
    }

    public static String getMessage(String messageKey, Object... args) {
        return AppUtils.messageSource.getMessage(messageKey, args, LocaleContextHolder.getLocale());
    }

    public static DateTimeFormatter localDateFormatter() {
        return localDateFormatter;
    }

    public static <T> List<ValidationError> validate(T object) {
        return StreamEx.of(validator.validate(object))
                       .map(ValidationError::from)
                       .toList();
    }

    public static String getAppVersion() {
        return appVersion;
    }
}
