package com.veact.recruitment.api;

import com.veact.recruitment.api.controller.MovieController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MovieMicroserviceApplication {

	public static void main(String[] args) {
		new SpringApplication(MovieMicroserviceApplication.class).run(args);
	}

}
