package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

import com.google.gson.Gson;

public class Response {
    private Headers headers;
    private HttpExchange httpExchange;
    private OutputStream body;
    private final String CONTENT_TYPE = "application/json; charset=utf-8";
    private int statusCode;
    private Map<String, String> jsonMap = new HashMap<>();

    public Response(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        this.headers = httpExchange.getResponseHeaders();
        this.body = httpExchange.getResponseBody();

        setHeaders(CONTENT_TYPE);
    }

    public void setError(HttpStatus httpStatus) {
        this.jsonMap.put("error", httpStatus.getMessage());
    }

    public void setData(String key, String value) {
        this.jsonMap.put(key, value);
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setHeaders(String contentType) {
        this.headers.set("Content-Type", contentType);
    }

    public void send() {
        Gson gson = new Gson();

        try {
            this.httpExchange.sendResponseHeaders(this.statusCode, gson.toJson(jsonMap).length());
            this.body.write(gson.toJson(jsonMap).getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to send response", e);
        }
    }
}
