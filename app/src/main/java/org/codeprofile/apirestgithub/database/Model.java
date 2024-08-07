package org.codeprofile.apirestgithub.database;

import java.io.InputStreamReader;

import org.codeprofile.apirestgithub.interfaces.ApiGitHubInterface;
import org.codeprofile.shared.http.ApiResponse;
import org.codeprofile.shared.http.Response;
import org.codeprofile.shared.utils.ArgumentsContext;

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
