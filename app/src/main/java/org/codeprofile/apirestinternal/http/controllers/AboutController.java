package org.codeprofile.apirestinternal.http.controllers;

import org.codeprofile.apirestinternal.models.About;
import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.http.Controller;
import org.codeprofile.shared.http.Request;
import org.codeprofile.shared.http.Response;

public class AboutController extends Controller {
    @Override
    public Response get(Request request, Response response) {
        response.setHttpStatus(HttpStatus.OK);

        About about = new About();

        response.setData(about);

        return response;
    }
}
