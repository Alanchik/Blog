package com.chahan.blog.exception.handling;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ApiError {

    private int status;
    private String message;
}
