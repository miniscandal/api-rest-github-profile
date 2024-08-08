package org.codeprofile.internal.http.controllers;

import org.codeprofile.internal.models.Documentation;
import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.http.Request;
import org.codeprofile.shared.http.Response;

import com.google.gson.Gson;

public class DocumentationController extends Controller {
    @Override
    public Response get(Request request, Response response) {
        response.setHttpStatus(HttpStatus.OK);

        Documentation documentation = new Documentation();

        Gson gson = new Gson();

        String json = gson.toJson(documentation);

        response.setData(json.getBytes());

        return response;
    }
}
