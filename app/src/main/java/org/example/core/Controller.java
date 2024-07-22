package org.example.core;

import java.io.IOException;

import java.util.List;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements HttpHandler {
    private String apiGitHubBasePath;
    private String path;
    private Class<?> model;
    private List<String> parameters = new ArrayList<String>();

    public Controller() {
        if (this instanceof ApiGitHub) {
            ApiGitHub apiGitHub = (ApiGitHub) this;
            this.apiGitHubBasePath = apiGitHub.getApiBasePath();
            this.model = apiGitHub.getModel();
        }
    }

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

    public String getApiGitHubBasePath() {
        return apiGitHubBasePath;
    }

    public Class<?> getModel() {
        return model;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Response response = new Response(httpExchange);
        Request request = new Request(httpExchange);

        if (isRequestPathMatching(request.getPathContext())) {
            get(request, response).send();
        } else {
            sendNotFoundResponse(response);
        }
    }

    private boolean isRequestPathMatching(String pathContext) {
        return pathContext.equalsIgnoreCase(this.path);
    }

    private void sendNotFoundResponse(Response response) throws IOException {
        response.setData("{\"message\": \"Document not found!\"}");
        response.send();
    }
}
