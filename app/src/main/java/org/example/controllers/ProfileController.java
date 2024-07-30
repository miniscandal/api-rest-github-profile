package org.example.controllers;

import org.example.core.Controller;
import org.example.core.HttpStatus;
import org.example.core.Request;
import org.example.core.Response;
import org.example.core.ApiGitHub;
import org.example.core.ApiResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ProfileController extends Controller {
    private static final String BASE_PATH = "/users/miniscandal";

    @Override
    public Response get(Request request, Response response) {
        ApiResponse apiResponse = ApiGitHub.getResponse(BASE_PATH);
        response.setStatusCode(apiResponse.getStatusCode());
        response.setError(HttpStatus.OK);
        String bmo;
        try {
            bmo = new String(apiResponse.getBody().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(bmo);
            response.setData("descendant", bmo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }
}
