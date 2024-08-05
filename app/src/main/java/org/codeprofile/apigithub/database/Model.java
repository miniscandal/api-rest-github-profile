package org.codeprofile.apigithub.database;

import java.io.InputStreamReader;

import org.codeprofile.apigithub.interfaces.ApiGitHubInterface;
import org.codeprofile.core.http.Response;
import org.codeprofile.shared.ApiResponse;
import org.codeprofile.shared.ArgumentsContext;

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
