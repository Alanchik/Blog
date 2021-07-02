package com.chahan.blog.exception_handling;

import com.chahan.blog.exception.BadRequestApiException;
import com.chahan.blog.exception.BaseApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            BadRequestApiException exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        data.setStatus(exception.getStatus().toString());
        return new ResponseEntity<>(data,exception.getStatus());
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(
            Exception exception) {
        IncorrectData data = new IncorrectData();
        data.setInfo(exception.getMessage());
        data.setStatus(HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

}
