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
import org.codeprofile.shared.utils.BasePath;

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

        String path = BasePath.formatPath(basePath, request.getParametersArguments());

        ApiResponse apiResponse = getResponse(path);

        System.out.println(apiResponse.geHttpStatus().getCode());
    }

    public ApiResponse getResponse(String path) {
        InputStream body;
        HttpStatus httpStatus;
        HttpResponse<InputStream> response;

        try {
            response = sendRequest(path);
            httpStatus = HttpStatus.fromCode(response.statusCode());
            body = response.statusCode() == HttpStatus.OK.getCode() ? response.body() : null;

        } catch (IOException | InterruptedException e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            body = null;
        }
        return new ApiResponse(httpStatus, body);

    }

    public HttpResponse<InputStream> sendRequest(String path) throws IOException, InterruptedException {
        HttpResponse<InputStream> response = null;

        String uri = BASE_URL + path;
        response = RequestExecutor.sendRequest(uri);

        return response;
    }
}
