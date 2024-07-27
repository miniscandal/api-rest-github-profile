package org.example.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import java.net.http.HttpResponse;

public class ApiGitHub {
    private static final String BASE_URL = "https://api.github.com";

    public static ApiResponse getResponse(String path) {
        String uri = BASE_URL + "/" + path;
        InputStream body = null;
        int statusCode = 0;

        try {
            HttpResponse<InputStream> response = RequestExecutor.sendRequest(uri);
            if (response.statusCode() != HttpStatus.OK.getCode()) {
                body = response.body();
                statusCode = HttpStatus.OK.getCode();
            } else {
                statusCode = HttpStatus.INTERNAL_SERVER_ERROR.getCode();
                String message = HttpStatus.INTERNAL_SERVER_ERROR.getMessage();
                String data = "{\"message\": " + "\"" + message + "\"}";
                body = new ByteArrayInputStream(data.getBytes());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return new ApiResponse(statusCode, body);
    }
}
