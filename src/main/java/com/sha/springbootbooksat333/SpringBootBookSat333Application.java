package com.sha.springbootbooksat333;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-${spring.profiles.active:default}.properties")
public class SpringBootBookSat333Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBookSat333Application.class, args);
	}

}
