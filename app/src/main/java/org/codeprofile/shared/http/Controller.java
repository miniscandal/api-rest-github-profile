package org.codeprofile.shared.http;

import java.util.List;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.interfaces.Service;

import java.io.IOException;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements HttpHandler {
    private List<String> parameters = List.of();
    private String path;
    private Service service;

    public abstract Response get(Request request, Response response);

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = List.copyOf(parameters);
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Response response = new Response(httpExchange);
        Request request = new Request(httpExchange);

        this.service.getAnime("relife");
        if (!request.getPathContext().equalsIgnoreCase(this.path)) {
            response.setHttpStatus(HttpStatus.NOT_FOUND_CONTEXT);
            response.send();

            return;
        }

        get(request, response).send();
    }
}
