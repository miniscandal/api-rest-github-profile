package org.example.controllers;

import org.example.core.Controller;
import org.example.core.HttpRequestParameters;
import org.example.models.Commit;

public class CommitController extends Controller {
    private static final String GITHUB_API_BASE_URI = "/repos/{name}/{repo}/commits";
    private static final Class<?> MODEL_CLASS = Commit.class;

    public CommitController() {
        super(GITHUB_API_BASE_URI, MODEL_CLASS);
    }

    @Override
    public byte[] processResponse(HttpRequestParameters requestParameters) {
        byte[] response = arrayObjectsResponse();

        return response;
    }
}
