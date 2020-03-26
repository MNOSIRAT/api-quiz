package io.mohammad.apiquiz.container.annotation;

import io.mohammad.apiquiz.container.config.InputValidatorConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Mohammad Nosairat (Feb 27, 2020)
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({InputValidatorConfiguration.class})
public @interface EnableInputValidator {
}
