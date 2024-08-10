package org.codeprofile.shared.http;

import java.util.List;

import org.codeprofile.shared.enums.HttpStatus;
import org.codeprofile.shared.strategies.ServiceStrategy;
import org.codeprofile.shared.contracts.Service;

import java.io.IOException;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

/*
 * Class Properties
 * 
 * parameters: son los parametros esperados que se definen en el contexto del controlador concreto
 * path: es el contexto sin parametros relacionado con el controlador concreto
 * example: "/document/{id}" donde /document es el contexto y {id} es el parametro que se espera
 * 
 */

public abstract class Controller implements HttpHandler {
    private Service service;
    private List<String> parameters;

    private String path;

    public abstract Response get(Request request, Response response);

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;

    }

    public List<String> getParameters() {
        return this.parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = List.copyOf(parameters);
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

        if (!request.getPath().startsWith(this.path + "/")) {
            response.setHttpStatus(HttpStatus.NOT_FOUND_CONTEXT);
            response.send();

            return;
        }

        request.setArguments(extractArguments(request.getPath()));

        if (this instanceof ServiceStrategy) {
            this.service.execute(request, response);
        }

        get(request, response).send();
    }

    public String[] extractArguments(String path) {
        return path.replace(this.path + "/", "").split("/");
    }
}
