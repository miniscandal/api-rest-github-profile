package org.example.controllers;

import org.example.core.Controller;
import org.example.core.RequestParameters;
import org.example.models.Profile;

public class ProfileController extends Controller {
    private static final String GITHUB_API_BASE_URI = "https://api.github.com/users/{name}";
    private static final Class<?> MODEL_CLASS = Profile.class;

    public ProfileController() {
        super(MODEL_CLASS, GITHUB_API_BASE_URI);
    }

    @Override
    public byte[] manageResponse(RequestParameters requestParameters) {
        byte[] response = singleObjectResponse();

        return response;
    }
}
