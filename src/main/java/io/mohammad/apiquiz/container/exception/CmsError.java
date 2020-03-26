package io.mohammad.apiquiz.container.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
@AllArgsConstructor
public enum CmsError {
    NOT_FOUND(HttpStatus.NOT_FOUND);

    @Getter
    private HttpStatus status;
}
