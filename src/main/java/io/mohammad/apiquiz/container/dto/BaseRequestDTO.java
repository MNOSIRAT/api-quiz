package io.mohammad.apiquiz.container.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.mohammad.apiquiz.container.exception.BadInputException;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author Mohammad Nosairat 3/24/2020
 */
public class BaseRequestDTO {
    @JsonIgnore
    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    @JsonIgnore
    protected void extraValidation() {
    }

    @JsonIgnore
    public final void validate() {
        BadInputException badInputException = new BadInputException();
        VALIDATOR.validate(this, new Class[0]).forEach((v) -> {
            badInputException.addError(v.getPropertyPath().toString(), v.getMessage());
        });

        try {
            this.extraValidation();
        } catch (BadInputException var3) {
            badInputException.getErrors().addAll(var3.getErrors());
        }

        if (badInputException.getErrors().size() > 0) {
            throw badInputException;
        }
    }

    @JsonIgnore
    protected void throwError(String path, String message) {
        throw (new BadInputException()).addError(path, message);
    }
}
