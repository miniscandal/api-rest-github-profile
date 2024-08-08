package org.codeprofile.apirestgithub.http.controllers;

import org.codeprofile.apirestgithub.interfaces.ApiGitHub;
import org.codeprofile.apirestgithub.models.Profile;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.http.Request;
import org.codeprofile.shared.http.Response;

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
