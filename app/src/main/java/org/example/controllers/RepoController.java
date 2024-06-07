package org.example.controllers;

import com.sun.net.httpserver.HttpExchange;

import org.example.core.Controller;
import org.example.models.Repo;

public class RepoController extends Controller {
    private String uri = "https://api.github.com/users/miniscandal/repos";

    public RepoController() {
        super(Repo.class);
    }

    @Override
    public void handle(HttpExchange exchange) {
        byte[] response = arrayObjectsResponse(uri);
        sendResponse(exchange, response);
    }
}
