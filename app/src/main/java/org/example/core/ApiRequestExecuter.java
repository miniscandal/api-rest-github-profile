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

public class ApiRequestExecuter {
    private static final HttpClient httpClient = HttpClient.newHttpClient();
    private Class<?> classOft;

    public ApiRequestExecuter(Class<?> classOft) {
        this.classOft = classOft;
    }

    public HttpResponse<InputStream> sendRequest(String uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();

        HttpResponse<InputStream> inputStream = null;

        try {
            inputStream = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return inputStream;
    }

    public byte[] handleStatusCode(int statusCode) {
        byte[] bytes = null;
        String message = null;
        Map<String, String> stringMap = new LinkedHashMap<>();
        Gson gson = new Gson();

        if (statusCode >= 400 && statusCode <= 499) {
            message = "Not Found";
        }

        if (statusCode >= 500 && statusCode <= 599) {
            message = "Internal Server Error";
        }
        stringMap.put("message", message);
        bytes = gson.toJson(stringMap).getBytes();

        return bytes;
    }

    public byte[] handleSingleObjectResponse(HttpResponse<InputStream> response) {
        int statusCode = response.statusCode();
        Gson gson = new Gson();
        byte[] bytes = null;

        if (statusCode >= 200 && statusCode <= 299) {
            InputStreamReader reader = new InputStreamReader(response.body());
            Object object = gson.fromJson(reader, classOft);
            bytes = gson.toJson(object).getBytes();
        } else {
            bytes = handleStatusCode(statusCode);
        }

        return bytes;
    }

    public byte[] handleArrayObjectResponse(HttpResponse<InputStream> response) {
        int statusCode = response.statusCode();
        Gson gson = new Gson();
        byte[] bytes = null;

        if (statusCode >= 200 && statusCode <= 299) {
            InputStreamReader reader = new InputStreamReader(response.body());
            Object objectArray = Array.newInstance(classOft, 0);
            Object[] objects = (Object[]) gson.fromJson(reader, objectArray.getClass());
            bytes = gson.toJson(objects).getBytes();
        } else {
            bytes = handleStatusCode(statusCode);
        }

        return bytes;
    }

    public byte[] singleObjectResponse(String uri) {
        HttpResponse<InputStream> response = sendRequest(uri);
        byte[] bytes = handleSingleObjectResponse(response);

        return bytes;
    }

    public byte[] arrayObjectResponse(String uri) {
        HttpResponse<InputStream> response = sendRequest(uri);
        byte[] bytes = handleArrayObjectResponse(response);

        return bytes;
    }
}
