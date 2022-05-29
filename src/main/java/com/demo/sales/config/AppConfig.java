package com.demo.sales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author Loi Nguyen
 *
 */
@Configuration
@ComponentScan("com.demo.sales")
public class AppConfig {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages/sales");
        source.setUseCodeAsDefaultMessage(true);

        return source;
    }
}
