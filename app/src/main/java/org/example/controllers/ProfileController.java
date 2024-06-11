package org.example.controllers;

import org.example.core.Controller;
import org.example.core.RequestParameters;
import org.example.models.Profile;

public class ProfileController extends Controller {
    private static final String BASE_URI = "https://api.github.com/users/{name}";
    private static final Class<?> classOft = Profile.class;

    public ProfileController() {
        super(classOft, BASE_URI);
    }

    @Override
    public byte[] manageResponse(RequestParameters requestParameters) {
        byte[] response = singleObjectResponse();

        return response;
    }
}
