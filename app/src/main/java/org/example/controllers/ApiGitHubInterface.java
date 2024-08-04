package org.example.controllers;

import org.example.models.Model;

public interface ApiGitHubInterface<T extends Model> {
    String getBasePath();

    Class<T> getModel();
}
