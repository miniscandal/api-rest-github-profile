package org.codeprofile.shared.http;

import com.sun.net.httpserver.HttpExchange;

public class Request {
    private String path;

    private String[] arguments;

    public Request(HttpExchange httpExchange) {
        this.path = httpExchange.getRequestURI().getPath();
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
