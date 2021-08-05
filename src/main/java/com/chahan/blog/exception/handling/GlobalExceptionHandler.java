package com.chahan.blog.exception.handling;

import com.chahan.blog.exception.BaseApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(
            Exception exception) {
        ApiError data = new ApiError();
        data.setMessage(exception.getMessage());
        data.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(
            BaseApiException exception) {
        ApiError data = new ApiError();
        data.setMessage(exception.getMessage());
        data.setStatus(exception.getStatus().value());
        return new ResponseEntity<>(data, exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleException(
            MethodArgumentNotValidException exception) {
        ApiError data = new ApiError();
        data.setMessage(exception.getMessage());
        data.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
