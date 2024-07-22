package org.example.core;

import java.util.List;
import java.util.ArrayList;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;

public class Request {
    // private Headers headers;
    // private String method;
    private String path;
    private String pathContext;
    private List<String> pathArguments;

    public Request(HttpExchange httpExchange) {
        initializeRequestMetadata(httpExchange);
        initializePathDetails();
    }

    // public String getMethod() {
    // return this.method;
    // }

    // public Headers getHeaders() {
    // return this.headers;
    // }

    public String getPathContext() {
        return this.pathContext;
    }

    public List<String> getPathArguments() {
        return this.pathArguments;
    }

    private List<String> retrievePathSegments() {
        Path inputPath = Paths.get(this.path);
        List<String> segments = new ArrayList<String>();

        for (int i = 0; i < inputPath.getNameCount(); i++) {
            segments.add(inputPath.getName(i).toString());
        }

        return List.copyOf(segments);
    }

    private void initializeRequestMetadata(HttpExchange httpExchange) {
        // this.headers = httpExchange.getRequestHeaders();
        // this.method = httpExchange.getRequestMethod();
        this.path = httpExchange.getRequestURI().getPath();
    }

    private void initializePathDetails() {
        List<String> pathSegments = retrievePathSegments();
        this.pathContext = "/" + pathSegments.get(0);
        this.pathArguments = List.copyOf(pathSegments.subList(1, pathSegments.size()));
    }
}
