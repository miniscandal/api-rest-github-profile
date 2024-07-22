package org.example.controllers;

import org.example.core.Controller;
import org.example.core.Request;
import org.example.core.Response;

import org.example.models.Profile;

import org.example.core.ApiGitHub;

public class ProfileController extends Controller implements ApiGitHub {
    private static final String API_BASE_PATH = "/users/{name}";
    private static final Class<?> MODEL = Profile.class;

    public String getApiBasePath() {
        return API_BASE_PATH;
    }

    public Class<?> getModel() {
        return MODEL;
    }

    @Override
    public Response get(Request request, Response response) {
        response.setData("{\"message\": \"Igna!\"}");

        return response;
    }
}
