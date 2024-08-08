package org.codeprofile.apirestgithub.database;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.http.HttpResponse;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.http.ApiResponse;
import org.codeprofile.shared.http.RequestExecutor;
import org.codeprofile.shared.http.Response;
import org.codeprofile.shared.utils.ArgumentsContext;
import org.codeprofile.apirestgithub.interfaces.ApiGitHub;

import com.google.gson.Gson;

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

    public static <T extends Model> Response useApiGitHubService(
            ApiGitHub<T> apiGitHub,
            ArgumentsContext argumentsContext,
            Response response) {

        String basePath = apiGitHub.getBasePath();
        Class<T> model = apiGitHub.getModel();

        String pathWithArgument = argumentsContext.applyArgumentsInPath(basePath);

        ApiResponse apiResponse = ApiGitHubService.get(pathWithArgument);

        if (apiResponse.getBody() != null) {
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader(apiResponse.getBody());
            byte[] apiResponseBytes = gson.toJson(gson.fromJson(reader, model)).getBytes();

            response.setData(apiResponseBytes);
        }
        response.setHttpStatus(apiResponse.geHttpStatus());

        return response;
    }
}
