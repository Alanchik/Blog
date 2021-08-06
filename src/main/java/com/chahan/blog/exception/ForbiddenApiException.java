package com.chahan.blog.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class ForbiddenApiException extends BaseApiException {

    public ForbiddenApiException(String message) {
        super(FORBIDDEN, message);
    }
}
