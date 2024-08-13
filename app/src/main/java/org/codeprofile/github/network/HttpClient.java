package org.codeprofile.github.network;

import java.io.IOException;
import java.io.InputStream;

import java.net.http.HttpResponse;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.integration.ApiResponse;
import org.codeprofile.shared.network.RequestExecutor;

public class HttpClient {

    public static ApiResponse get(String uri) {
        try {
            HttpResponse<InputStream> response = RequestExecutor.get(uri);
            HttpStatus httpStatus = HttpStatus.fromCode(response.statusCode());
            InputStream body = response.statusCode() == HttpStatus.OK.getCode()
                    ? response.body()
                    : null;
            return new ApiResponse(httpStatus, body);
        } catch (IOException | InterruptedException e) {
            return new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
