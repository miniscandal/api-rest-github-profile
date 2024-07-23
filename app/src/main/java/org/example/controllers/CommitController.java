package org.example.controllers;

import org.example.core.ApiGitHub;
import org.example.core.Controller;
import org.example.core.Request;
import org.example.core.Response;

import org.example.models.Commit;

public class CommitController extends Controller {
    private static final String BASE_PATH = "/repos/{name}/{repo}/commits";
    private static final Class<?> MODEL = Commit.class;
    private ApiGitHub apiGitHub = new ApiGitHub(BASE_PATH, MODEL);

    @Override
    public Response get(Request request, Response response) {
        response.setData(apiGitHub.getData());
        response.setData("{\"message\": \"Commit!\"}");

        return response;
    }
}
