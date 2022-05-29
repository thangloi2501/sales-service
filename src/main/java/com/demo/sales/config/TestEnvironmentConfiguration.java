package com.demo.sales.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.demo.sales.utils.AppUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

/**
 * @author Loi Nguyen
 *
 */
public class TestEnvironmentConfiguration {

    @Bean
    public Validator validator(MessageSource messageSource) {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource);

        return validator;
    }

    @Bean
    public ObjectWriter objectWriter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);

        return mapper.writer().withDefaultPrettyPrinter();
    }

    @Bean
    public MessageSource messageSource() {
        return new MessageSource() {
            @Override
            public String getMessage(String code, Object[] args, String defaultMessage, Locale locale) {
                return code;
            }

            @Override
            public String getMessage(String code, Object[] args, Locale locale) {
                return code;
            }

            @Override
            public String getMessage(MessageSourceResolvable resolvable, Locale locale) {
                return String.join(",", resolvable.getCodes());
            }
        };
    }

    @Bean
    public AppUtils appUtils(MessageSource messageSource, Validator validator) throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<AppUtils> contructor = AppUtils.class.getDeclaredConstructor(MessageSource.class, Validator.class, String.class, String.class);
        ReflectionUtils.makeAccessible(contructor);
        return contructor.newInstance(messageSource, validator, "1.0", "dd.MM.yy");
    }
}
