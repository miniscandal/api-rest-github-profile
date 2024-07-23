package org.example.core;

public class ApiGitHub {
    private String basePath;
    private Class<?> model;
    private String data = "descendant";

    public ApiGitHub(String basePath, Class<?> model) {
        this.basePath = basePath;
        this.model = model;
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Class<?> getModel() {
        return model;
    }

    public void setModel(Class<?> model) {
        this.model = model;
    }

    public String getData() {
        return this.data;
    }
}
