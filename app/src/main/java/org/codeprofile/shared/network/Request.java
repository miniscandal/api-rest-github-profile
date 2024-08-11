package org.codeprofile.shared.integration;

import java.util.Map;

import com.sun.net.httpserver.HttpExchange;

public class Request {
    private String path;

    private String[] arguments;

    private Map<String, String> parametersArguments;

    public Request(HttpExchange httpExchange) {
        this.path = httpExchange.getRequestURI().getPath();
    }

    public Map<String, String> getParametersArguments() {
        return parametersArguments;
    }

    public void setParametersArguments(Map<String, String> parametersArguments) {
        this.parametersArguments = parametersArguments;
    }

    public String[] getArguments() {
        return this.arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
