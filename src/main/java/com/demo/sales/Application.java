package com.demo.sales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Loi Nguyen
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.demo.sales"})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}