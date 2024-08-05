package org.example.models;

import java.io.InputStreamReader;

import org.example.controllers.ApiGitHubInterface;
import org.example.core.ApiGitHubService;
import org.example.core.ApiResponse;
import org.example.core.ArgumentsContext;
import org.example.core.Response;

import com.google.gson.Gson;

public abstract class Model {

    public static <T extends Model> Response useApiGitHubService(
            ApiGitHubInterface<T> apiGitHubInterface,
            ArgumentsContext argumentsContext,
            Response response) {

        String basePath = apiGitHubInterface.getBasePath();
        Class<T> model = apiGitHubInterface.getModel();

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
