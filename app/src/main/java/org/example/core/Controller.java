package org.example.core;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;

import org.example.controllers.ApiGitHubInterface;
import org.example.models.Model;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

public abstract class Controller implements HttpHandler {
    private List<String> parameters = new ArrayList<>();
    private String path;

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

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Response response = new Response(httpExchange);
        Request request = new Request(httpExchange);

        if (this instanceof ApiGitHubInterface) {
            handleApiGitHubInterface((ApiGitHubInterface<?>) this, request, response);
        }

        if (request.getPathContext().equalsIgnoreCase(this.path)) {
            get(request, response).send();
        } else {
            sendNotFoundResponse(response, HttpStatus.NOT_FOUND);
        }
    }

    private void sendNotFoundResponse(Response response, HttpStatus httpStatus) throws IOException {
        response.setHttpStatus(httpStatus);
        response.send();
    }

    private <T extends Model> void handleApiGitHubInterface(
            ApiGitHubInterface<T> apiGitHubInterface,
            Request request,
            Response response) throws IOException {

        ArgumentsContext argumentsContext;
        argumentsContext = new ArgumentsContext(this.parameters, request.getPathArguments());

        String basePath = apiGitHubInterface.getBasePath();
        Class<?> model = apiGitHubInterface.getModel();

        String pathWithArgument = argumentsContext.applyArgumentsInPath(basePath);

        ApiResponse apiResponse = new ApiGitHub().getResponse(pathWithArgument);

        response.setApiResponse(apiResponse, model);
    }
}
