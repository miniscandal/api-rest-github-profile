package org.codeprofile.apirestgithub.interfaces;

import org.codeprofile.apirestgithub.database.Model;

public interface ApiGitHubInterface<T extends Model> {
    String getBasePath();

    Class<T> getModel();
}
