package io.mohammad.apiquiz.container.handler;

import io.mohammad.apiquiz.container.exception.BadInputException;
import io.mohammad.apiquiz.container.exception.ManagedException;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.annotation.Priority;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mohammad Nosairat 3/24/2020
 */
@Slf4j
@ControllerAdvice
@Priority(10)
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleBadInputException(BadInputException ex) {
        log.error("unexpected exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler({ManagedException.class})
    public ResponseEntity handleManagedException(ManagedException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(ex.getError().getStatus()).body(response);
    }

}
