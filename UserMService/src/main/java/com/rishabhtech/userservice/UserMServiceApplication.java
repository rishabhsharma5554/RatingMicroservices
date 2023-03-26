package com.rishabhtech.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserMServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserMServiceApplication.class, args);
	}
}
