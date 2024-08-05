package org.codeprofile.shared;

import java.io.InputStream;

import org.codeprofile.core.http.HttpStatus;

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
