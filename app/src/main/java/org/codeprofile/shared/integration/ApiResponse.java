package org.codeprofile.shared.integration;

import java.io.InputStream;

import org.codeprofile.shared.enums.HttpStatus;

public class ApiResponse {
    private HttpStatus httpStatus;
    private InputStream body;

    public ApiResponse(HttpStatus httpStatus, InputStream body) {
        this.httpStatus = httpStatus;
        this.body = body;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public InputStream getBody() {
        return this.body;
    }
}
