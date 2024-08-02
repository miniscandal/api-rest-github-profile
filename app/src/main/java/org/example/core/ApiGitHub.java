package org.example.core;

import java.io.IOException;
import java.io.InputStream;

import java.net.http.HttpResponse;

public class ApiGitHub {
    private static final String BASE_URL = "https://api.github.com";

    public static ApiResponse getResponse(String path) {
        HttpResponse<InputStream> response;
        InputStream body = null;
        HttpStatus httpStatus;

        try {
            response = RequestExecutor.sendRequest(BASE_URL + path);
            httpStatus = HttpStatus.fromCode(response.statusCode());

            if (response.statusCode() == HttpStatus.OK.getCode()) {
                body = response.body();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("no internet");
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ApiResponse(httpStatus, body);
    }
}
