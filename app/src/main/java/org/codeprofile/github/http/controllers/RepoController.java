package org.codeprofile.github.http.controllers;

import org.codeprofile.github.contracts.ApiGitHub;
import org.codeprofile.github.models.Repo;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.network.Request;
import org.codeprofile.shared.network.Response;

public class RepoController extends Controller implements ApiGitHub<Repo> {
    @Override
    public String getBasePath() {
        return "/repos/{profile}/{name}";
    }

    @Override
    public Class<Repo> getModel() {
        return Repo.class;
    }

    @Override
    public Response get(Request request, Response response) {

        return response;
    }
}
