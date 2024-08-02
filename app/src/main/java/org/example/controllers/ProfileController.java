package org.example.controllers;

import org.example.core.Controller;
import org.example.core.Request;
import org.example.core.Response;
import org.example.core.ApiGitHub;
import org.example.core.ApiResponse;

import org.example.models.Profile;

public class ProfileController extends Controller {
    private static final String BASE_PATH = "/users/miniscandal";

    @Override
    public Response get(Request request, Response response) {
        ApiResponse apiResponse = ApiGitHub.getResponse(BASE_PATH);

        response.setHttpStatus(apiResponse.geHttpStatus());
        response.setApiResponse(apiResponse, Profile.class);

        return response;
    }
}
