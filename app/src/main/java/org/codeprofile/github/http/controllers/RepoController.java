package org.codeprofile.github.http.controllers;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.integration.Request;
import org.codeprofile.shared.integration.Response;

public class RepoController extends Controller {
    // private static final String BASE_PATH = "/repos/{name}/{repo}/commits";

    @Override
    public Response get(Request request, Response response) {
        response.setHttpStatus(HttpStatus.OK);
        response.setData("anime", "spy x family");
        return response;
    }
}
