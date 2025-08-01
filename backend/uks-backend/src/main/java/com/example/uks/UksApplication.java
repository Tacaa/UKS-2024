package com.example.uks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class UksApplication {

	public static void main(String[] args) {
		SpringApplication.run(UksApplication.class, args);
	}

}
