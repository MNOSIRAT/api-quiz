package io.mohammad.apiquiz.container.config;

import io.mohammad.apiquiz.container.aspect.ControllerArgumentValidator;
import io.mohammad.apiquiz.container.handler.InvalidInputExceptionHandler;
import org.springframework.context.annotation.Bean;

/**
 * @author Mohammad Nosairat 3/24/2020
 */
public class InputValidatorConfiguration {
    public InputValidatorConfiguration() {
    }

    @Bean
    public ControllerArgumentValidator controllerArgumentValidator() {
        return new ControllerArgumentValidator();
    }

    @Bean
    public InvalidInputExceptionHandler invalidInputExceptionHandler() {
        return new InvalidInputExceptionHandler() {};
    }
}

