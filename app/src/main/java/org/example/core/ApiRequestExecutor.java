package org.example.core;

import java.util.Map;

import java.util.LinkedHashMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.lang.reflect.Array;

import com.google.gson.Gson;

public class ApiRequestExecutor {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private Class<?> responseType;

    public ApiRequestExecutor(Class<?> responseType) {
        this.responseType = responseType;
    }

    public HttpResponse<InputStream> sendRequest(String uri) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<InputStream> inputStream = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());

        return inputStream;
    }

    public byte[] handleStatusCode(int statusCode) {
        Map<String, String> errorResponse = new LinkedHashMap<>();
        Gson gson = new Gson();
        String message = null;

        if (statusCode >= 400 && statusCode <= 499) {
            message = "Not Found";
        } else if (statusCode >= 500 && statusCode <= 599) {
            message = "Internal Server Error";
        } else {
            message = "Unknown Error";
        }

        errorResponse.put("message", message);

        return gson.toJson(errorResponse).getBytes();
    }

    public byte[] handleSingleObjectResponse(HttpResponse<InputStream> response) throws IOException {
        int statusCode = response.statusCode();
        Gson gson = new Gson();
        byte[] bytes = null;

        if (statusCode >= 200 && statusCode <= 299) {
            InputStreamReader reader = new InputStreamReader(response.body());
            Object object = gson.fromJson(reader, responseType);
            bytes = gson.toJson(object).getBytes();
        } else {
            bytes = handleStatusCode(statusCode);
        }

        return bytes;
    }

    public byte[] handleArrayObjectResponse(HttpResponse<InputStream> response) throws IOException {
        int statusCode = response.statusCode();
        Gson gson = new Gson();

        if (statusCode >= 200 && statusCode <= 299) {
            InputStreamReader reader = new InputStreamReader(response.body());
            Object objectArray = Array.newInstance(responseType, 0);
            Object[] objects = (Object[]) gson.fromJson(reader, objectArray.getClass());

            return gson.toJson(objects).getBytes();
        } else {

            return handleStatusCode(statusCode);
        }
    }

    public byte[] executeSingleObjectResponse(String uri) throws IOException, InterruptedException {
        HttpResponse<InputStream> response = sendRequest(uri);

        return handleSingleObjectResponse(response);
    }

    public byte[] executeArrayObjectResponse(String uri) throws IOException, InterruptedException {
        HttpResponse<InputStream> response = sendRequest(uri);

        return handleArrayObjectResponse(response);
    }
}
