package org.example.controllers;

import org.example.core.Controller;
import org.example.core.HttpRequestParameters;
import org.example.models.Repo;

public class RepoController extends Controller {
    private static final String GITHUB_API_BASE_URI = "https://api.github.com/users/{name}/repos";
    private static final Class<?> MODEL_CLASS = Repo.class;

    public RepoController() {
        super(GITHUB_API_BASE_URI, MODEL_CLASS);
    }

    @Override
    public byte[] processResponse(HttpRequestParameters requestParameters) {
        byte[] response = arrayObjectsResponse();

        return response;
    }
}
