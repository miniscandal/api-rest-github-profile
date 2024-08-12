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
 * Define la logica funcional base para todos los controladores.
 * Maneja las peticiones y las respuestas http de los clientes.
 * 
 * 
 * Properties
 * 
 * @parameters: son los parametros esperados que se definen en el contexto del controlador concreto
 * @path: es el contexto sin parametros relacionado con el controlador concreto. * 
 * example: "/document/{id}" donde /document es el contexto y {id} es el parametro que se espera.
 * 
 */

public abstract class Controller implements HttpHandler {
    private Service service;
    private String[] parameters;

    private String path;

    /*
     * Method handle GET request
     * 
     * @param request the http
     * 
     * @param response the http
     */

    public abstract Response get(Request request, Response response);

    public String getPath() {
        return path;
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

        if (!request.getPath().startsWith(this.path + "/")) {
            response.setHttpStatus(HttpStatus.NOT_FOUND);
            response.send();

            return;
        }

        if (this.parameters.length >= 0 && this.parameters != null) {
            String[] arguments = extractArguments(request.getPath());

            if (arguments.length != this.parameters.length) {
                response.setHttpStatus(HttpStatus.BAD_REQUEST);
                response.send();

                return;
            }

            request.setArguments(arguments);
            request.setParametersArguments(createMap(this.parameters, arguments));
        }

        if (this instanceof ServiceStrategy) {
            this.service.execute(request, response);
        }

        get(request, response).send();
    }

    public Map<String, String> createMap(String[] parameters, String[] arguments) {
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < arguments.length; i++) {
            map.put(parameters[i], arguments[i]);
        }

        return map;
    }

    public String[] extractArguments(String path) {
        return path.replace(this.path + "/", "").split("/");
    }
}
