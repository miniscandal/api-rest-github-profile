package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;

import org.example.core.Controller;
import org.example.models.Commit;

public class CommitController extends Controller {
    private String uri = "https://api.github.com/repos/miniscandal/api-rest-github-profile/commits";

    public CommitController() {
        super(Commit.class);
    }

    @Override
    public void handle(HttpExchange exchange) {
        byte[] response = arrayObjectsResponse(uri);
        sendResponse(exchange, response);
    }
}
