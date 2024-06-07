package org.example.core;

import java.io.IOException;
import java.io.OutputStream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class Controller implements HttpHandler {
    public HttpClientHandle httpClientHandle;

    public Controller(Class<?> classOft) {
        httpClientHandle = new HttpClientHandle(classOft);
    }

    public byte[] singleObjectResponse(String uri) {
        return httpClientHandle.singleObjectResponse(uri);
    }

    public byte[] arrayObjectsResponse(String uri) {
        return httpClientHandle.arrayObjectResponse(uri);
    }

    public Object getRequestParameters(HttpExchange httpExchange, Class<?> classOft) {
        String query = httpExchange.getRequestURI().getQuery();
        Pattern pattern = Pattern.compile("([^&=]+)=([^&]+)");
        Matcher matcher = pattern.matcher(query);
        JsonObject jsonObject = new JsonObject();
        while (matcher.find()) {
            String key = matcher.group(1);
            String value = matcher.group(2);
            jsonObject.addProperty(key, value);
        }

        Gson gson = new Gson();
        Object object = gson.fromJson(jsonObject, classOft);

        return object;
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
