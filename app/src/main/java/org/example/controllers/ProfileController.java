package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;

import org.example.core.Controller;
import org.example.models.Profile;

public class ProfileController extends Controller {
    private String uri = "https://api.github.com/users";

    public ProfileController() {
        super(Profile.class);
    }

    @Override
    public void handle(HttpExchange exchange) {
        RequestParameters parameters = (RequestParameters) getRequestParameters(exchange, RequestParameters.class);
        String profileName = parameters.getName();
        byte[] response = singleObjectResponse(uri + "/" + profileName);
        sendResponse(exchange, response);
    }

    class RequestParameters {
        private String name;

        public String getName() {
            return name;
        }
    }
}
