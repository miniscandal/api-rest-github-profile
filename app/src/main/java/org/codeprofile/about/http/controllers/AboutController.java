package org.codeprofile.about.http.controllers;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.http.Request;
import org.codeprofile.shared.http.Response;

public class AboutController extends Controller {
    @Override
    public Response get(Request request, Response response) {
        response.setHttpStatus(HttpStatus.OK);

        response.setData("messaeg", "Hello World!");

        return response;
    }
}
