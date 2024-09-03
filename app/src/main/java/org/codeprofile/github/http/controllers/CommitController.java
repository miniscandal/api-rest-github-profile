package org.codeprofile.github.http.controllers;

import org.codeprofile.github.contracts.ApiGitHub;
import org.codeprofile.github.models.Commit;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.network.Request;
import org.codeprofile.shared.network.Response;

public class CommitController extends Controller implements ApiGitHub<Commit> {
    @Override
    public String getBasePath() {
        return "/repos/{profile}/{name}/commits";
    }

    @Override
    public Class<Commit> getModel() {
        return Commit.class;
    }

    @Override
    public Response get(Request request, Response response) {

        return response;
    }
}
