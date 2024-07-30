package org.example.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

import java.net.http.HttpResponse;

import org.example.models.UnsuccessfulResponse;

import com.google.gson.Gson;

public class ApiGitHub {
    private static final String BASE_URL = "https://api.github.com";

    public static ApiResponse getResponse(String path) {
        HttpResponse<InputStream> response;
        InputStream body;
        int statusCode;

        try {
            response = RequestExecutor.sendRequest(BASE_URL + path);
            statusCode = response.statusCode();
            HttpStatus httpStatus = HttpStatus.fromCode(statusCode);

            if (statusCode == HttpStatus.OK.getCode()) {
                body = response.body();
            } else {
                body = handleUnsuccessfulResponse(httpStatus);
            }
        } catch (IOException | InterruptedException e) {
            statusCode = HttpStatus.SERVICE_UNAVAILABLE.getCode();
            body = handleUnsuccessfulResponse(HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ApiResponse(statusCode, body);
    }

    private static ByteArrayInputStream handleUnsuccessfulResponse(HttpStatus httpStatus) {
        Gson gson = new Gson();
        UnsuccessfulResponse unsuccessfulResponse = new UnsuccessfulResponse();
        unsuccessfulResponse.setStatus(httpStatus.getCode());
        unsuccessfulResponse.setMessage(httpStatus.getMessage());
        return new ByteArrayInputStream(gson.toJson(unsuccessfulResponse).getBytes());
    }
}
