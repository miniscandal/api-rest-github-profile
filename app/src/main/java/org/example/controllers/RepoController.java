package org.example.controllers;

import org.example.core.Controller;
import org.example.core.HttpStatus;
import org.example.core.Request;
import org.example.core.Response;

public class RepoController extends Controller {
    // private static final String BASE_PATH = "/repos/{name}/{repo}/commits";

    @Override
    public Response get(Request request, Response response) {
        response.setHttpStatus(HttpStatus.OK);
        response.setData("anime", "spy x family");
        return response;
    }
}
