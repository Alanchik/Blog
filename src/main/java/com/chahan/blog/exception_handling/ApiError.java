package com.chahan.blog.exception_handling;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter

public class ApiError {
    private int status;
    private String message;


}
