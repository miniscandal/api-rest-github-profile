package org.codeprofile.internal.http.controllers;

import org.codeprofile.internal.models.Documentation;
import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.network.Request;
import org.codeprofile.shared.network.Response;

public class DocumentationController extends Controller {
    @Override
    public Response get(Request request, Response response) {
        response.setHttpStatus(HttpStatus.OK);

        Documentation documentation = new Documentation();

        response.setData(documentation);

        return response;
    }
}
