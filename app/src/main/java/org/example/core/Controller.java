package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class Controller implements HttpHandler {
    public HttpClientHandle httpClientHandle;

    public abstract String getUri();

    public Controller(Class<?> classOft) {
        httpClientHandle = new HttpClientHandle(classOft);
    }

    public byte[] singleObjectResponse() {
        String uri = getUri();

        return httpClientHandle.singleObjectResponse(uri);
    }

    public byte[] arrayObjectsResponse() {
        String uri = getUri();

        return httpClientHandle.arrayObjectResponse(uri);
    }

    public void sendResponse(HttpExchange httpExchange, byte[] bytes) {
        Headers headers = httpExchange.getResponseHeaders();

        headers.set("Content-Type", "application/json; charset=utf-8");

        try (OutputStream outputStream = httpExchange.getResponseBody()) {
            String method = httpExchange.getRequestMethod();
            if (method.equalsIgnoreCase("HEAD")) {
                httpExchange.sendResponseHeaders(204, -1);
                return;
            }
            if (method.equalsIgnoreCase("GET")) {
                httpExchange.sendResponseHeaders(200, bytes.length);
                outputStream.write(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
    }
}
