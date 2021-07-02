package com.chahan.blog.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseApiException extends RuntimeException {

    private final HttpStatus status;

    protected BaseApiException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
