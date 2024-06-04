package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;

import org.example.core.Controller;
import org.example.models.Profile;

public class ProfileController extends Controller {
    private String uri = "https://api.github.com/users/miniscandal";

    public ProfileController() {
        super(Profile.class);
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public void handle(HttpExchange exchange) {
        byte[] response = singleObjectResponse();
        sendResponse(exchange, response);
    }
}
