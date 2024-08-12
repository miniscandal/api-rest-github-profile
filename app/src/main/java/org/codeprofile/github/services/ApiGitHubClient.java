package org.codeprofile.github.services;

import org.codeprofile.github.database.Model;
import org.codeprofile.github.network.HttpClient;
import org.codeprofile.shared.contracts.Service;
import org.codeprofile.shared.integration.ApiResponse;
import org.codeprofile.shared.network.Request;
import org.codeprofile.shared.network.Response;
import org.codeprofile.shared.utils.BasePath;

public class ApiGitHubClient<T extends Model> implements Service {
    private static final String BASE_URL = "https://api.github.com";

    private String basePath;

    private Class<T> model;

    public ApiGitHubClient(String basePath, Class<T> model) {
        this.basePath = basePath;
        this.model = model;
    }

    @Override
    public void execute(Request request, Response response) {
        String path = BasePath.formatPath(this.basePath, request.getParametersArguments());
        ApiResponse apiResponse = HttpClient.getResponse(BASE_URL + path);

        if (apiResponse.getBody() != null) {
            response.setData(apiResponse.getBody(), model);
        }

        response.setHttpStatus(apiResponse.getHttpStatus());
    }
}
