package org.example.core;

import java.io.InputStream;

public class ApiResponse {
    private int statusCode;
    private InputStream body;

    public ApiResponse(int statusCode, InputStream body) {
        this.statusCode = statusCode;
        this.body = body;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public InputStream getBody() {
        return this.body;
    }
}
