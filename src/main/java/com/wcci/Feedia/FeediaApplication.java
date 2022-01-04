package com.wcci.Feedia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FeediaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FeediaApplication.class, args);
//		SpringApplicationBuilder builder = new SpringApplicationBuilder(FeediaApplication.class);
//		builder.headless(false).run(args);
	}

}
