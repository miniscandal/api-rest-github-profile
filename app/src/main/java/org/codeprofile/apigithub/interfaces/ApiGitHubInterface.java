package org.codeprofile.apigithub.interfaces;

import org.codeprofile.apigithub.database.Model;

public interface ApiGitHubInterface<T extends Model> {
    String getBasePath();

    Class<T> getModel();
}
