package org.codeprofile.shared.http;

import java.util.List;

import org.codeprofile.apigithub.database.Model;
import org.codeprofile.apigithub.interfaces.ApiGitHubInterface;
import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.utils.ArgumentsContext;

import java.util.ArrayList;

import java.io.IOException;

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

        ArgumentsContext argumentsContext;
        argumentsContext = new ArgumentsContext(this.parameters, request.getPathArguments());

        if (!request.getPathContext().equalsIgnoreCase(this.path)) {
            sendNotFoundResponse(response);
        }

        if (this instanceof ApiGitHubInterface) {
            handleApiGitHubService(response, argumentsContext);
        }

        get(request, response).send();
    }

    private void sendNotFoundResponse(Response response) throws IOException {
        response.setHttpStatus(HttpStatus.NOT_FOUND);
        response.send();
    }

    public void handleApiGitHubService(Response response, ArgumentsContext argumentsContext) {

        ApiGitHubInterface<?> apiGitHubInterface = (ApiGitHubInterface<?>) this;

        Model.useApiGitHubService(apiGitHubInterface, argumentsContext, response);
    }
}
