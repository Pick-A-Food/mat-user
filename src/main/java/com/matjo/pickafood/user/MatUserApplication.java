package com.matjo.pickafood.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class MatUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatUserApplication.class, args);
	}

}
