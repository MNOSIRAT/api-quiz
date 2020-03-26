package io.mohammad.apiquiz.container.handler;

import io.mohammad.apiquiz.container.exception.BadInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Priority;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Nosairat 3/24/2020
 */
@ControllerAdvice
@Priority(5)
public abstract class InvalidInputExceptionHandler {

    @ExceptionHandler({BadInputException.class})
    @ResponseBody
    public ResponseEntity handleBadInputException(BadInputException ex) {
        Map<String,Object> response = new HashMap<>();
        response.put("violations",ex.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}