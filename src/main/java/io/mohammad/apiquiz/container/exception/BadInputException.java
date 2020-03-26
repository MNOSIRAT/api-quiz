package io.mohammad.apiquiz.container.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mohammad Nosairat 3/24/2020
 */
public class BadInputException extends RuntimeException {
    private List<BadInputException.FieldError> errors = new ArrayList();

    public BadInputException() {
    }

//    public BadInputException(String message) {
//        super(message);
//    }

    public BadInputException addError(String path, String message) {
        this.errors.add(new BadInputException.FieldError(path, message));
        return this;
    }

//    public static BadInputException of(String path, String message) {
//        return (new BadInputException()).addError(path, message);
//    }

    public List<FieldError> getErrors() {
        return this.errors;
    }

    public String toString() {
        return "BadInputException(errors=" + this.getErrors() + ")";
    }

    public static class FieldError {
        public String field;
        public String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String toString() {
            return "BadInputException.FieldError(field=" + this.field + ", message=" + this.message + ")";
        }
    }
}
