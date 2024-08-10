package org.codeprofile.github.services;

import java.io.IOException;
import java.io.InputStream;

import java.net.http.HttpResponse;

import org.codeprofile.github.database.Model;
import org.codeprofile.shared.contracts.Service;
import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.http.ApiResponse;
import org.codeprofile.shared.http.Request;
import org.codeprofile.shared.http.RequestExecutor;
import org.codeprofile.shared.http.Response;

public class ApiGitHubClient<T extends Model> implements Service {
    private static final String BASE_URL = "https://api.github.com";

    private String basePath;

    @SuppressWarnings("unused")
    private Class<T> model;

    public ApiGitHubClient(String basePath, Class<T> model) {
        this.basePath = basePath;
        this.model = model;
    }

    @Override
    public void execute(Request request, Response response) {
        response.setData("basePath: ", this.basePath);
        response.setHttpStatus(HttpStatus.OK);
    }

    public ApiResponse getResponse(Request request) {
        InputStream body;
        HttpStatus httpStatus;
        HttpResponse<InputStream> response;

        try {

            response = sendRequest(request);
            httpStatus = HttpStatus.fromCode(response.statusCode());
            body = response.statusCode() == HttpStatus.OK.getCode() ? response.body() : null;

        } catch (IOException | InterruptedException e) {

            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = null;

        }
        return new ApiResponse(httpStatus, body);

    }

    public HttpResponse<InputStream> sendRequest(Request request) throws IOException, InterruptedException {
        HttpResponse<InputStream> response = null;

        response = RequestExecutor.sendRequest(BASE_URL + "users/miniscandal");

        return response;
    }
}
