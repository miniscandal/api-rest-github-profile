package org.example.controllers;

import org.example.core.ApiGitHub;
import org.example.core.Controller;
import org.example.core.Request;
import org.example.core.Response;

public class CommitController extends Controller {
    private static final String BASE_PATH = "/repos/{name}/{repo}/commits";

    @Override
    public Response get(Request request, Response response) {
        ApiGitHub.getResponse(BASE_PATH);
        response.setData("{\"message\": \"Commit!\"}");

        return response;
    }
}
