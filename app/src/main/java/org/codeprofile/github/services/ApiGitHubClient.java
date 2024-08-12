package org.codeprofile.github.services;

import java.io.InputStreamReader;

import org.codeprofile.github.database.Model;
import org.codeprofile.github.network.HttpClient;
import org.codeprofile.shared.contracts.Service;
import org.codeprofile.shared.integration.ApiResponse;
import org.codeprofile.shared.network.Request;
import org.codeprofile.shared.network.Response;
import org.codeprofile.shared.utils.BasePath;

import com.google.gson.Gson;

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

        String uri = BASE_URL + path;

        ApiResponse apiResponse = HttpClient.getResponse(uri);

        if (apiResponse.getBody() != null) {
            Gson gson = new Gson();
            InputStreamReader reader = new InputStreamReader(apiResponse.getBody());
            byte[] bytes = gson.toJson(gson.fromJson(reader, model)).getBytes();

            response.setData(bytes);
        }
        response.setHttpStatus(apiResponse.geHttpStatus());
    }

}
