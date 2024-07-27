package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

public class Response {
    @SuppressWarnings("unused")
    private Headers headers;
    private HttpExchange httpExchange;
    private OutputStream body;
    private byte[] data;
    private final String CONTENT_TYPE = "application/json; charset=utf-8";
    private int statusCode;

    public Response(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        this.headers = configureHeaders(httpExchange);
        this.body = httpExchange.getResponseBody();
    }

    public byte[] getData() {
        return this.data;
    }

    public void setData(String json) {
        isJsonValid(json);
        System.out.println(json);
        this.data = json.getBytes();
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void send() {
        try {
            httpExchange.sendResponseHeaders(this.statusCode, this.data.length);
            this.body.write(this.data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void isJsonValid(String json) {
        try {
            JsonParser.parseString(json);
        } catch (JsonSyntaxException e) {
            throw new RuntimeException("Error json format" + e.getMessage());
        }
    }

    private Headers configureHeaders(HttpExchange httpExchange) {
        Headers headers = httpExchange.getResponseHeaders();
        headers.set("Content-Type", CONTENT_TYPE);

        return headers;
    }
}
