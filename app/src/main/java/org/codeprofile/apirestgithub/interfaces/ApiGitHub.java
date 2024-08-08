package org.codeprofile.apirestgithub.interfaces;

import org.codeprofile.apirestgithub.database.Model;
import org.codeprofile.apirestgithub.services.ApiGitHubService;
import org.codeprofile.shared.interfaces.Service;
import org.codeprofile.shared.interfaces.ServiceStrategy;

public interface ApiGitHub<T extends Model> extends ServiceStrategy {
    String getBasePath();

    Class<T> getModel();

    @Override
    default Service getService() {
        return new ApiGitHubService();
    }
}
