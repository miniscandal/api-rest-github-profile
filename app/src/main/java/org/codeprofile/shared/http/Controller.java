package org.codeprofile.shared.http;

import java.util.Map;
import java.util.HashMap;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.network.Request;
import org.codeprofile.shared.network.Response;
import org.codeprofile.shared.strategies.ServiceStrategy;
import org.codeprofile.shared.contracts.Service;

import java.io.IOException;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

/*
 * Class Responsibility
 * 
 * Base class for all controllers.
 * Handle HTTP request and response from clients.
 * 
 * Implementation notes
 * 
 * @parameters: These are the expected parameters defined within the context of the specific controller.
 * @path: This is the context without parameters related to the specific controller.
 * example: "/document/{id}" where /document is the context and {id} is the expected parameter.
 * 
 */

public abstract class Controller implements HttpHandler {
    private Service service;
    private String[] parameters;
    private String path;

    public abstract Response get(Request request, Response response);

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;

    }

    public String[] getParameters() {
        return this.parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Response response = new Response(httpExchange);
        Request request = new Request(httpExchange);

        if (!isContextRoute(request, response)) {
            return;
        }

        boolean parametersExpected = this.parameters.length > 0 && this.parameters != null;

        if (!parametersExpected || !isNumberExpectedArguments(request, response)) {
            return;
        }

        if (this instanceof ServiceStrategy) {
            this.service.execute(request, response);
        }

        get(request, response).send();
    }

    private boolean isContextRoute(Request request, Response response) {
        boolean isContextRoute = request.getPath().startsWith(this.path + "/");

        if (!isContextRoute) {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.send();
        }

        return isContextRoute;
    }

    private boolean isNumberExpectedArguments(Request request, Response response) {
        String[] arguments = extractArguments(request.getPath());
        boolean expected = arguments.length == this.parameters.length;

        if (expected) {
            request.setArguments(arguments);
            request.setParametersArguments(createMap(this.parameters, arguments));
        } else {
            response.setHttpStatus(HttpStatus.BAD_REQUEST);
            response.send();

        }

        return expected;
    }

    private Map<String, String> createMap(String[] parameters, String[] arguments) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < arguments.length; i++) {
            map.put(parameters[i], arguments[i]);
        }

        return map;
    }

    private String[] extractArguments(String path) {
        return path.replace(this.path + "/", "").split("/");
    }

}
