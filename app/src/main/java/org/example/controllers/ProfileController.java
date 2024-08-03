package org.example.controllers;

import org.example.core.Controller;
import org.example.core.Request;
import org.example.core.Response;
import org.example.models.Profile;

public class ProfileController extends Controller implements ApiGitHubInterface {
    @Override
    public String getBasePath() {
        return "/users/{name}";
    }

    @Override
    public Class<?> getModel() {
        return Profile.class;
    }

    @Override
    public Response get(Request request, Response response) {

        return response;
    }
}
