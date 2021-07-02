package com.chahan.blog.exception_handling;

import org.springframework.http.HttpStatus;

public class IncorrectData {
    private String status;
    private String info;

    public IncorrectData() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
