package org.codeprofile.internal.models;

public class Endpoint {
    private String path;
    private String method;
    private String description;

    public String getPath() {
        return path;
    }

    public String getMethod() {
        return method;
    }

    public String getDescription() {
        return description;
    }

    public Endpoint(String path, String method, String description) {
        this.path = path;
        this.method = method;
        this.description = description;
    }
}
