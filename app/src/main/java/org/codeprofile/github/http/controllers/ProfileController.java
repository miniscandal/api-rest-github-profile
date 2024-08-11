package org.codeprofile.github.http.controllers;

import org.codeprofile.github.contracts.ApiGitHub;
import org.codeprofile.github.models.Profile;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.network.Request;
import org.codeprofile.shared.network.Response;

public class ProfileController extends Controller implements ApiGitHub<Profile> {
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
