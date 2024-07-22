package org.example.controllers;

import org.example.core.Controller;
import org.example.core.Request;
import org.example.core.Response;

public class RepoController extends Controller {

    @Override
    public Response get(Request request, Response response) {
        response.setData("{\"message\": \"Mal!\"}");
        return response;
    }
}
