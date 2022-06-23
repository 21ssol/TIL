package com.example.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.*"})
public class AiWebTest2Application {

	public static void main(String[] args) {
		SpringApplication.run(AiWebTest2Application.class, args);
	}

}
