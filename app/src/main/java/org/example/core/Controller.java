package org.example.core;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements HttpHandler {
    private String path;
    private List<String> parameters = new ArrayList<String>();

    public abstract Response get(Request request, Response response);

    public void setPath(String path) {
        this.path = path;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = List.copyOf(parameters);
    }

    public List<String> getParameters() {
        return parameters;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Response response = new Response(httpExchange);
        Request request = new Request(httpExchange);

        if (request.getPathContext().equalsIgnoreCase(this.path)) {
            get(request, response).send();
        } else {
            sendNotFoundResponse(response);
        }
    }

    private void sendNotFoundResponse(Response response) throws IOException {
        response.setError(HttpStatus.NOT_FOUND);
        response.setStatusCode(HttpStatus.NOT_FOUND.getCode());
        response.send();
    }
}
