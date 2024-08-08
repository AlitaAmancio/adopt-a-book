package com.bookAdoption.adoptabook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.bookAdoption.adoptabook")
@PropertySource("classpath:application.properties")


public class AdoptABookApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptABookApplication.class, args);
	}

}
