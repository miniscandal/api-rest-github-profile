package org.codeprofile.apirestinternal.models;

import java.util.ArrayList;
import java.util.List;

import org.codeprofile.apirestinternal.database.Model;

public class Documentation extends Model {
    private String version;
    private List<Endpoint> endpoints;
    private String ofitialDocumentation;

    public Documentation() {
        this.version = "1.0.0";
        this.ofitialDocumentation = "https://api.github.url";
        this.endpoints = new ArrayList<>();
        this.endpoints.add(new Endpoint("/about", "GET", "Get information about the API"));
        this.endpoints.add(new Endpoint("/users", "GET", "Retrieve a list of users"));
        this.endpoints.add(new Endpoint("/users", "POST", "Create a new user"));
    }

    public String getVersion() {
        return version;
    }

    public String getOfitialDocumentation() {
        return ofitialDocumentation;
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

}
