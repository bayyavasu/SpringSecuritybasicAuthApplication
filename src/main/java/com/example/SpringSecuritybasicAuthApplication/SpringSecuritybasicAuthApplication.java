package com.example.SpringSecuritybasicAuthApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class SpringSecuritybasicAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecuritybasicAuthApplication.class, args);
	}

}
