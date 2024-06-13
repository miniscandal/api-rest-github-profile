package org.example.controllers;

import org.example.core.Controller;
import org.example.core.RequestParameters;
import org.example.models.Profile;

public class ProfileController extends Controller {
    /* ok */

    private static final String GITHUB_API_BASE_URI = "https://api.github.com/users/{name}";
    private static final Class<?> MODEL_CLASS = Profile.class;

    public ProfileController() {
        super(GITHUB_API_BASE_URI, MODEL_CLASS);
    }

    /* ok */

    @Override
    public byte[] manageResponse(RequestParameters requestParameters) {
        byte[] response = singleObjectResponse();

        return response;
    }
}
