package org.codeprofile.github.network;

import java.io.IOException;
import java.io.InputStream;

import java.net.http.HttpResponse;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.integration.ApiResponse;
import org.codeprofile.shared.network.RequestExecutor;

public class HttpClient {

    public static ApiResponse getResponse(String uri) {
        InputStream body;
        HttpStatus httpStatus;

        HttpResponse<InputStream> response;
        try {
            response = RequestExecutor.sendRequest(uri);
            httpStatus = HttpStatus.fromCode(response.statusCode());
            body = response.statusCode() == HttpStatus.OK.getCode() ? response.body() : null;
        } catch (IOException | InterruptedException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = null;
        }

        return new ApiResponse(httpStatus, body);

    }
}
