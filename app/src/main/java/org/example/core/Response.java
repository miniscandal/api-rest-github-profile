package org.example.core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;

import java.util.Map;

import java.util.HashMap;

import java.nio.charset.StandardCharsets;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

import com.google.gson.Gson;

public class Response {
    private static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private static final String CHARSET_NAME = StandardCharsets.UTF_8.name();

    private Headers headers;
    private HttpExchange httpExchange;
    private OutputStream body;
    private Map<String, String> jsonMap = new HashMap<>();

    private String statusMessage;
    private int statusCode;
    private HttpStatus httpStatus;

    private byte[] apiResponse = null;

    public Response(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        this.headers = httpExchange.getResponseHeaders();
        this.body = httpExchange.getResponseBody();
        setHeaders(CONTENT_TYPE);
    }

    public void setMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public void setData(String key, String value) {
        this.jsonMap.put(key, value);
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.statusCode = this.httpStatus.getCode();
        this.statusMessage = this.httpStatus.getMessage();
    }

    public void setHeaders(String contentType) {
        this.headers.set("Content-Type", contentType);
    }

    public void setApiResponse(ApiResponse apiResponse, Class<?> model) {
        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(apiResponse.getBody());

        this.apiResponse = gson.toJson(gson.fromJson(reader, model)).getBytes();
    }

    public void send() {
        Gson gson = new Gson();

        this.jsonMap.put("message", this.statusMessage);
        this.jsonMap.put("status", Integer.toString(this.statusCode));

        try {
            byte[] jsonBytes = gson.toJson(jsonMap).getBytes(CHARSET_NAME);

            if (this.apiResponse != null) {
                jsonBytes = this.apiResponse;
            }
            this.httpExchange.sendResponseHeaders(this.statusCode, jsonBytes.length);
            this.body.write(jsonBytes);
        } catch (IOException e) {
            throw new RuntimeException("Failed to send response", e);
        }
    }
}
