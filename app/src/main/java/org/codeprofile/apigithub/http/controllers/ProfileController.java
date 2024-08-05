package org.codeprofile.apigithub.http.controllers;

import org.codeprofile.core.http.Controller;
import org.codeprofile.core.http.Request;
import org.codeprofile.core.http.Response;
import org.codeprofile.apigithub.interfaces.ApiGitHubInterface;
import org.codeprofile.apigithub.models.Profile;

public class ProfileController extends Controller implements ApiGitHubInterface<Profile> {
    @Override
    public String getBasePath() {
        return "/users/{name}";
    }

    @Override
    public Class<Profile> getModel() {
        return Profile.class;
    }

    @Override
    public Response get(Request request, Response response) {

        return response;
    }
}
