package io.mohammad.apiquiz.container.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Mohammad Nosairat 3/25/2020
 */
public class ManagedException extends RuntimeException {
    @Getter
    private final CmsError error;

    public ManagedException(CmsError error, String message) {
        super(message);
        this.error = error;
    }
}
