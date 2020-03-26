package io.mohammad.apiquiz;

import io.mohammad.apiquiz.container.annotation.EnableInputValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableInputValidator
@SpringBootApplication
public class ApiQuizApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiQuizApplication.class, args);
	}

}
