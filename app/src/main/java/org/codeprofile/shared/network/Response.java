package org.codeprofile.shared.network;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;

import java.util.Map;

import org.codeprofile.shared.database.Model;
import org.codeprofile.shared.enums.ExceptionMessage;
import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.exceptions.FailedSendResponse;

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
    private byte[] data = null;

    private HttpStatus httpStatus;

    public Response(HttpExchange httpExchange) {
        this.httpExchange = httpExchange;
        this.headers = httpExchange.getResponseHeaders();
        this.headers.set("Content-Type", CONTENT_TYPE);
        this.body = httpExchange.getResponseBody();
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.statusCode = this.httpStatus.getCode();
        this.statusMessage = this.httpStatus.getMessage();
    }

    public void setData(String key, String value) {
        this.jsonMap.put(key, value);
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public void setData(Model model) {
        Gson gson = new Gson();
        String json = gson.toJson(model);

        this.data = json.getBytes();
    }

    public void setData(String message) {
        this.data = message.getBytes();
    }

    public <T extends Model> void setData(InputStream inputStream, Class<T> model) {
        Gson gson = new Gson();
        InputStreamReader reader = new InputStreamReader(inputStream);
        this.data = gson.toJson(gson.fromJson(reader, model)).getBytes();
    }

    public void send() {
        Gson gson = new Gson();

        if (this.jsonMap.isEmpty() || this.jsonMap.size() == 0) {
            this.jsonMap.put("message", this.statusMessage);
            this.jsonMap.put("status", Integer.toString(this.statusCode));
        }

        try {
            byte[] jsonBytes;

            if (this.data == null || this.data.length == 0) {
                jsonBytes = gson.toJson(jsonMap).getBytes(CHARSET_NAME);
            } else {
                jsonBytes = this.data;
            }

            this.httpExchange.sendResponseHeaders(this.statusCode, jsonBytes.length);
            this.body.write(jsonBytes);
        } catch (IOException e) {
            FailedSendResponse exception = new FailedSendResponse(ExceptionMessage.SEND_RESPONSE.getMessage(), e);
            System.out.println(exception.getMainMessage());
            throw exception;
        }
    }
}
