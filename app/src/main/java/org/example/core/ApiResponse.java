package org.example.core;

import java.io.InputStream;

public class ApiResponse {
    private HttpStatus httpStatus;
    private InputStream body;

    public ApiResponse(HttpStatus httpStatus, InputStream body) {
        this.httpStatus = httpStatus;
        this.body = body;
    }

    public HttpStatus geHttpStatus() {
        return this.httpStatus;
    }

    public InputStream getBody() {
        return this.body;
    }
}
