package org.codeprofile.apigithub.database;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.http.ApiResponse;
import org.codeprofile.shared.http.RequestExecutor;

public class ApiGitHubService {
    private static final String BASE_URL = "https://api.github.com";

    public static ApiResponse get(String path) {
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
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ApiResponse(httpStatus, body);
    }
}
