package com.rajan.dreamshops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class DreamShopsApplication {

	public static void main(String[] args) {

		SpringApplication.run(DreamShopsApplication.class, args);
		System.out.println("Hello World!");
	}

}
